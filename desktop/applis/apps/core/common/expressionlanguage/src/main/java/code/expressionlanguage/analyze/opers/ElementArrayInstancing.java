package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ClassMethodIdReturn;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class ElementArrayInstancing extends AbstractArrayInstancingOperation implements PreAnalyzableOperation {

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private CustList<PartOffset> partOffsetsErr = new CustList<PartOffset>();
    private String typeInfer = EMPTY_STRING;
    public ElementArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void preAnalyze(ContextEl _an) {
        String me_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _an);
        setClassName(_an.getStandards().getAliasObject());
        KeyWords keyWords_ = _an.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = me_.trim().substring(new_.length());
        int local_ = StringList.getFirstPrintableCharIndex(className_);
        String type_;
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        OperationNode m_ = par_.getOperation();
        int nbParentsInfer_ = par_.getNbParentsInfer();
        String typeAff_;
        AnalyzedBlock cur_ = _an.getAnalyzing().getCurrentAnaBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_an);
        } else if (m_ == null && cur_ instanceof ImportForEachLoop) {
            ImportForEachLoop i_ = (ImportForEachLoop) cur_;
            typeAff_ = i_.getImportedClassName();
            if (!typeAff_.isEmpty()) {
                typeAff_ = StringExpUtil.getPrettyArrayType(typeAff_);
            }
        } else {
            typeAff_ = tryGetTypeAff(m_);
        }
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        DimComp dim_ = AnaTemplates.getComponentForm(className_);
        String inferForm_ = AnaTemplates.getInferForm(dim_.getComponent());
        if (inferForm_ == null) {
            int loc_ = StringList.getFirstPrintableCharIndex(className_);
            String res_ = ResolvingImportTypes.resolveCorrectTypeWithoutErrors(_an,new_.length()+loc_,className_,true,partOffsets_);
            if (res_.startsWith(ARR)) {
                partOffsets.addAllElts(partOffsets_);
                typeInfer = res_;
                setClassName(StringExpUtil.getQuickComponentType(res_));
            }
            return;
        }
        type_ = ResolvingImportTypes.resolveAccessibleIdTypeWithoutError(_an,new_.length()+local_,inferForm_);
        partOffsets_.addAllElts(_an.getAnalyzing().getCurrentParts());
        if (type_.isEmpty()) {
            return;
        }

        if (dim_.getDim() == 0) {
            return;
        }
        StringMap<String> vars_ = new StringMap<String>();
        if (m_ instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
            String name_ = n_.getName();
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                RetrieveMethod f_ = (RetrieveMethod) call_;
                NameParametersFilter filter_ = buildQuickFilter(call_);
                CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                int len_ = methodInfos_.size();
                StringList candidates_ = new StringList();
                for (int i = 0; i < len_; i++) {
                    int gr_ = methodInfos_.get(i).size();
                    CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                    for (int j = 0; j < gr_; j++) {
                        MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                        String format_ = tryParamFormat(filter_,methodInfo_, name_, nbParentsInfer_+dim_.getDim(), type_, vars_, _an);
                        if (format_ == null) {
                            continue;
                        }
                        candidates_.add(format_);
                        newList_.add(methodInfo_);
                    }
                    methodInfos_.set(i,newList_);
                }
                if (candidates_.onlyOneElt()) {
                    String infer_ = StringExpUtil.getPrettyArrayType(candidates_.first(), dim_.getDim());
                    partOffsets.addAllElts(partOffsets_);
                    int begin_ = new_.length()+local_+className_.indexOf('<');
                    int end_ = new_.length()+local_+className_.indexOf('>')+1;
                    ContextUtil.appendTitleParts(_an,begin_,end_,infer_,partOffsets);
                    typeInfer = infer_;
                    setClassName(StringExpUtil.getQuickComponentType(infer_));
                }
            }
            if (call_ instanceof RetrieveConstructor) {
                RetrieveConstructor f_ = (RetrieveConstructor) call_;
                NameParametersFilter filter_ = buildQuickFilter(call_);
                CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                int len_ = methodInfos_.size();
                StringList candidates_ = new StringList();
                CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
                for (int i = 0; i < len_; i++) {
                    ConstructorInfo methodInfo_ = methodInfos_.get(i);
                    String format_ = tryParamFormat(filter_,methodInfo_, name_, nbParentsInfer_+dim_.getDim(), type_, vars_, _an);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.clear();
                methodInfos_.addAllElts(newList_);
                if (candidates_.onlyOneElt()) {
                    String infer_ = StringExpUtil.getPrettyArrayType(candidates_.first(), dim_.getDim());
                    partOffsets.addAllElts(partOffsets_);
                    int begin_ = new_.length()+local_+className_.indexOf('<');
                    int end_ = new_.length()+local_+className_.indexOf('>')+1;
                    ContextUtil.appendTitleParts(_an,begin_,end_,infer_,partOffsets);
                    typeInfer = infer_;
                    setClassName(StringExpUtil.getQuickComponentType(infer_));
                }
            }
            return;
        }
        if (m_ instanceof RetrieveMethod){
            RetrieveMethod f_ = (RetrieveMethod) m_;
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = getDeltaCount(firstChild_);
            int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
            CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
            int len_ = methodInfos_.size();
            StringList candidates_ = new StringList();
            for (int i = 0; i < len_; i++) {
                int gr_ = methodInfos_.get(i).size();
                CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                for (int j = 0; j < gr_; j++) {
                    MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                    String format_ = tryFormat(methodInfo_, indexChild_, nbParentsInfer_+dim_.getDim(), type_, vars_, _an);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.set(i,newList_);
            }
            if (candidates_.onlyOneElt()) {
                String infer_ = StringExpUtil.getPrettyArrayType(candidates_.first(), dim_.getDim());
                partOffsets.addAllElts(partOffsets_);
                int begin_ = new_.length()+local_+className_.indexOf('<');
                int end_ = new_.length()+local_+className_.indexOf('>')+1;
                ContextUtil.appendTitleParts(_an,begin_,end_,infer_,partOffsets);
                typeInfer = infer_;
                setClassName(StringExpUtil.getQuickComponentType(infer_));
            }
            return;
        }
        if (m_ instanceof RetrieveConstructor){
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = getDeltaCount(firstChild_);
            int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
            CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
            int len_ = methodInfos_.size();
            StringList candidates_ = new StringList();
            CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
            for (int i = 0; i < len_; i++) {
                ConstructorInfo methodInfo_ = methodInfos_.get(i);
                String format_ = tryFormat(methodInfo_, indexChild_, nbParentsInfer_+dim_.getDim(), type_, vars_, _an);
                if (format_ == null) {
                    continue;
                }
                candidates_.add(format_);
                newList_.add(methodInfo_);
            }
            methodInfos_.clear();
            methodInfos_.addAllElts(newList_);
            if (candidates_.onlyOneElt()) {
                String infer_ = StringExpUtil.getPrettyArrayType(candidates_.first(), dim_.getDim());
                partOffsets.addAllElts(partOffsets_);
                int begin_ = new_.length()+local_+className_.indexOf('<');
                int end_ = new_.length()+local_+className_.indexOf('>')+1;
                ContextUtil.appendTitleParts(_an,begin_,end_,infer_,partOffsets);
                typeInfer = infer_;
                setClassName(StringExpUtil.getQuickComponentType(infer_));
            }
            return;
        }
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (isUndefined(typeAff_, keyWordVar_)) {
            return;
        }
        String cp_ = StringExpUtil.getQuickComponentType(typeAff_, nbParentsInfer_+dim_.getDim());
        if (isNotCorrectDim(cp_)) {
            return;
        }
        String infer_ = AnaTemplates.tryInfer(type_,vars_, cp_, _an);
        if (infer_ == null) {
            return;
        }
        String arr_ = StringExpUtil.getPrettyArrayType(infer_, dim_.getDim());
        partOffsets.addAllElts(partOffsets_);
        int begin_ = new_.length()+local_+className_.indexOf('<');
        int end_ = new_.length()+local_+className_.indexOf('>')+1;
        ContextUtil.appendTitleParts(_an,begin_,end_, arr_,partOffsets);
        typeInfer = arr_;
        setClassName(StringExpUtil.getQuickComponentType(arr_));
    }

    @Override
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String m_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        setClassName(_conf.getStandards().getAliasObject());
        KeyWords keyWords_ = _conf.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = m_.trim().substring(new_.length());
        if (typeInfer.isEmpty()) {
            int loc_ = StringList.getFirstPrintableCharIndex(className_);
            className_ = ResolvingImportTypes.resolveCorrectType(_conf,new_.length()+loc_,className_);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
        } else {
            className_ = typeInfer;
        }
        if (!className_.startsWith(ARR)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //key word len
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    className_);
            _conf.getAnalyzing().getLocalizer().addError(un_);
            IntTreeMap<String> operators_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.firstKey(), _conf);
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            partOffsetsErr.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
            partOffsetsErr.add(new PartOffset("</a>",i_+1));
            String obj_ = _conf.getStandards().getAliasObject();
            obj_ = StringExpUtil.getPrettyArrayType(obj_);
            ClassArgumentMatching class_ = new ClassArgumentMatching(obj_);
            setResultClass(class_);
            return;
        }
        StringMap<StringList> map_;
        map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        String eltType_ = StringExpUtil.getQuickComponentType(className_);
        Mapping mapping_ = new Mapping();
        mapping_.setParam(eltType_);
        for (OperationNode o: chidren_) {
            int index_ = getPartOffsetsChildren().size();
            IntTreeMap<String> operators_ = getOperations().getOperators();
            CustList<PartOffset> parts_ = new CustList<PartOffset>();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(index_), _conf);
            ClassArgumentMatching argType_ = o.getResultClass();
            mapping_.setArg(argType_);
            mapping_.setMapping(map_);
            if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_conf, eltType_, argType_);
                if (res_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    argType_.getImplicits().add(cl_);
                    argType_.setRootNumber(res_.getRootNumber());
                    argType_.setMemberNumber(res_.getMemberNumber());
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                    cast_.setIndexFile(i_);
                    //first separator char child
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(argType_.getNames(),"&"),
                            eltType_);
                    _conf.getAnalyzing().getLocalizer().addError(cast_);
                    parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",i_));
                    parts_.add(new PartOffset("</a>",i_+1));
                }
            }
            if (PrimitiveTypeUtil.isPrimitive(eltType_, _conf)) {
                o.getResultClass().setUnwrapObject(eltType_);
                o.cancelArgument();
            }
            getPartOffsetsChildren().add(parts_);
        }
        String arrayCl_ = className_;
        setClassName(StringExpUtil.getQuickComponentType(arrayCl_));
        setResultClass(new ClassArgumentMatching(arrayCl_));
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public CustList<PartOffset> getPartOffsetsErr() {
        return partOffsetsErr;
    }
}
