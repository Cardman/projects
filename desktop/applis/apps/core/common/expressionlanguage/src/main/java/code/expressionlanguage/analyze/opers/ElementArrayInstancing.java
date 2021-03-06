package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.options.KeyWords;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ElementArrayInstancing extends AbstractArrayInstancingOperation implements PreAnalyzableOperation {

    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private final CustList<PartOffset> partOffsetsErr = new CustList<PartOffset>();
    private String typeInfer = EMPTY_STRING;
    public ElementArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        String me_ = getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        setClassName(_page.getAliasObject());
        KeyWords keyWords_ = _page.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = me_.trim().substring(new_.length());
        int local_ = StringUtil.getFirstPrintableCharIndex(className_);
        String type_;
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        OperationNode m_ = par_.getOperation();
        int nbParentsInfer_ = par_.getNbParentsInfer();
        String typeAff_;
        if (m_ == null && _page.getCurrentBlock() instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_page);
        } else if (m_ == null && _page.getCurrentAnaBlockForEachLoop() != null) {
            ImportForEachLoop i_ = _page.getCurrentAnaBlockForEachLoop();
            typeAff_ = i_.getImportedClassName();
            if (!typeAff_.isEmpty()) {
                typeAff_ = StringExpUtil.getPrettyArrayType(typeAff_);
            }
        } else {
            typeAff_ = tryGetTypeAff(m_, par_.getOperationChild().getIndexChild());
        }
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        DimComp dim_ = AnaInherits.getComponentForm(className_);
        String inferForm_ = AnaTemplates.getInferForm(dim_.getComponent());
        if (inferForm_ == null) {
            int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
            String res_ = ResolvingTypes.resolveCorrectTypeWithoutErrors(new_.length()+loc_,className_,true,partOffsets_, _page);
            String comp_ = StringExpUtil.getQuickComponentType(res_);
            if (comp_ != null) {
                partOffsets.addAllElts(partOffsets_);
                typeInfer = res_;
                setClassName(comp_);
            }
            return;
        }
        type_ = ResolvingTypes.resolveAccessibleIdTypeWithoutError(new_.length()+local_,inferForm_, _page);
        partOffsets_.addAllElts(_page.getCurrentParts());
        if (type_.isEmpty()) {
            return;
        }

        int dimArr_ = dim_.getDim();
        if (dimArr_ == 0) {
            return;
        }
        StringMap<String> vars_ = new StringMap<String>();
        boolean list_ = false;
        if (m_ instanceof ArgumentListInstancing){
            list_ = true;
            m_ = m_.getParent().getParent();
        }
        if (m_ instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
            String name_ = n_.getName();
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                RetrieveMethod f_ = (RetrieveMethod) call_;
                NameParametersFilter filter_ = buildQuickFilter(_page,call_);
                CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                int len_ = methodInfos_.size();
                StringList candidates_ = new StringList();
                for (int i = 0; i < len_; i++) {
                    int gr_ = methodInfos_.get(i).size();
                    CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                    for (int j = 0; j < gr_; j++) {
                        MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                        String format_ = tryParamFormat(filter_,methodInfo_, name_, nbParentsInfer_+ dimArr_, type_, vars_, _page);
                        if (format_ == null) {
                            continue;
                        }
                        candidates_.add(format_);
                        newList_.add(methodInfo_);
                    }
                    methodInfos_.set(i,newList_);
                }
                if (candidates_.onlyOneElt()) {
                    String infer_ = StringExpUtil.getPrettyArrayType(candidates_.first(), dimArr_);
                    partOffsets.addAllElts(partOffsets_);
                    int begin_ = new_.length()+local_+className_.indexOf('<');
                    int end_ = new_.length()+local_+className_.indexOf('>')+1;
                    ContextUtil.appendTitleParts(begin_,end_,infer_,partOffsets, _page);
                    typeInfer = infer_;
                    setClassName(StringExpUtil.getQuickComponentType(infer_));
                }
            }
            if (call_ instanceof RetrieveConstructor) {
                RetrieveConstructor f_ = (RetrieveConstructor) call_;
                NameParametersFilter filter_ = buildQuickFilter(_page,call_);
                CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                int len_ = methodInfos_.size();
                StringList candidates_ = new StringList();
                CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
                for (int i = 0; i < len_; i++) {
                    ConstructorInfo methodInfo_ = methodInfos_.get(i);
                    String format_ = tryParamFormat(filter_,methodInfo_, name_, nbParentsInfer_+ dimArr_, type_, vars_, _page);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.clear();
                methodInfos_.addAllElts(newList_);
                if (candidates_.onlyOneElt()) {
                    String infer_ = StringExpUtil.getPrettyArrayType(candidates_.first(), dimArr_);
                    partOffsets.addAllElts(partOffsets_);
                    int begin_ = new_.length()+local_+className_.indexOf('<');
                    int end_ = new_.length()+local_+className_.indexOf('>')+1;
                    ContextUtil.appendTitleParts(begin_,end_,infer_,partOffsets, _page);
                    typeInfer = infer_;
                    setClassName(StringExpUtil.getQuickComponentType(infer_));
                }
            }
            return;
        }
        if (m_ instanceof RetrieveMethod){
            RetrieveMethod f_ = (RetrieveMethod) m_;
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = getDeltaCount(list_,firstChild_);
            int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
            CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
            int len_ = methodInfos_.size();
            StringList candidates_ = new StringList();
            for (int i = 0; i < len_; i++) {
                int gr_ = methodInfos_.get(i).size();
                CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                for (int j = 0; j < gr_; j++) {
                    MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                    String format_ = tryFormat(methodInfo_, indexChild_, nbParentsInfer_+ dimArr_, type_, vars_, _page);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.set(i,newList_);
            }
            if (candidates_.onlyOneElt()) {
                String infer_ = StringExpUtil.getPrettyArrayType(candidates_.first(), dimArr_);
                partOffsets.addAllElts(partOffsets_);
                int begin_ = new_.length()+local_+className_.indexOf('<');
                int end_ = new_.length()+local_+className_.indexOf('>')+1;
                ContextUtil.appendTitleParts(begin_,end_,infer_,partOffsets, _page);
                typeInfer = infer_;
                setClassName(StringExpUtil.getQuickComponentType(infer_));
            }
            return;
        }
        if (m_ instanceof RetrieveConstructor){
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = getDeltaCount(list_,firstChild_);
            int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
            CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
            int len_ = methodInfos_.size();
            StringList candidates_ = new StringList();
            CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
            for (int i = 0; i < len_; i++) {
                ConstructorInfo methodInfo_ = methodInfos_.get(i);
                String format_ = tryFormat(methodInfo_, indexChild_, nbParentsInfer_+ dimArr_, type_, vars_, _page);
                if (format_ == null) {
                    continue;
                }
                candidates_.add(format_);
                newList_.add(methodInfo_);
            }
            methodInfos_.clear();
            methodInfos_.addAllElts(newList_);
            if (candidates_.onlyOneElt()) {
                String infer_ = StringExpUtil.getPrettyArrayType(candidates_.first(), dimArr_);
                partOffsets.addAllElts(partOffsets_);
                int begin_ = new_.length()+local_+className_.indexOf('<');
                int end_ = new_.length()+local_+className_.indexOf('>')+1;
                ContextUtil.appendTitleParts(begin_,end_,infer_,partOffsets, _page);
                typeInfer = infer_;
                setClassName(StringExpUtil.getQuickComponentType(infer_));
            }
            return;
        }
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (isUndefined(typeAff_, keyWordVar_)) {
            return;
        }
        String cp_ = StringExpUtil.getQuickComponentType(typeAff_, nbParentsInfer_+ dimArr_);
        if (isNotCorrectDim(cp_)) {
            return;
        }
        String infer_ = tryInferOrImplicit(type_,vars_, _page, cp_);
        if (infer_ == null) {
            return;
        }
        String arr_ = StringExpUtil.getPrettyArrayType(infer_, dimArr_);
        partOffsets.addAllElts(partOffsets_);
        int begin_ = new_.length()+local_+className_.indexOf('<');
        int end_ = new_.length()+local_+className_.indexOf('>')+1;
        ContextUtil.appendTitleParts(begin_,end_, arr_,partOffsets, _page);
        typeInfer = arr_;
        setClassName(StringExpUtil.getQuickComponentType(arr_));
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String m_ = getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        setClassName(_page.getAliasObject());
        KeyWords keyWords_ = _page.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = m_.trim().substring(new_.length());
        if (typeInfer.isEmpty()) {
            int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
            className_ = ResolvingTypes.resolveCorrectType(new_.length()+loc_,className_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
        } else {
            className_ = typeInfer;
        }
        String eltType_ = StringExpUtil.getQuickComponentType(className_);
        if (eltType_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    className_);
            _page.getLocalizer().addError(un_);
            StrTypes operators_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.firstKey(), _page);
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            partOffsetsErr.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
            partOffsetsErr.add(new PartOffset("</a>",i_+1));
            String obj_ = _page.getAliasObject();
            obj_ = StringExpUtil.getPrettyArrayType(obj_);
            AnaClassArgumentMatching class_ = new AnaClassArgumentMatching(obj_);
            setResultClass(class_);
            return;
        }
        StringMap<StringList> map_;
        map_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setParam(eltType_);
        for (OperationNode o: chidren_) {
            int index_ = getPartOffsetsChildren().size();
            StrTypes operators_ = getOperations().getOperators();
            CustList<PartOffset> parts_ = new CustList<PartOffset>();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(index_), _page);
            AnaClassArgumentMatching argType_ = o.getResultClass();
            mapping_.setArg(argType_);
            mapping_.setMapping(map_);
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(eltType_, argType_, _page);
                if (res_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    argType_.getImplicits().add(cl_);
                    argType_.setMemberId(res_.getMemberId());
                    argType_.setFunction(res_.getPair());
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                    int i_ = _page.getLocalizer().getCurrentLocationIndex();
                    cast_.setIndexFile(i_);
                    //first separator char child
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(argType_.getNames(),"&"),
                            eltType_);
                    _page.getLocalizer().addError(cast_);
                    parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",i_));
                    parts_.add(new PartOffset("</a>",i_+1));
                }
            }
            if (AnaTypeUtil.isPrimitive(eltType_, _page)) {
                o.getResultClass().setUnwrapObject(eltType_, _page.getPrimitiveTypes());
            }
            getPartOffsetsChildren().add(parts_);
        }
        String arrayCl_ = className_;
        setClassName(StringExpUtil.getQuickComponentType(arrayCl_));
        setResultClass(new AnaClassArgumentMatching(arrayCl_));
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public CustList<PartOffset> getPartOffsetsErr() {
        return partOffsetsErr;
    }
}
