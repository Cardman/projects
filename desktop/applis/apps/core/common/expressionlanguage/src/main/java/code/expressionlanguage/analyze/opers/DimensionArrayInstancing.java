package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.ReturnMethod;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class DimensionArrayInstancing extends
        AbstractArrayInstancingOperation implements PreAnalyzableOperation {
    private int countArrayDims;
    private String typeInfer = EMPTY_STRING;
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public DimensionArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        countArrayDims = getOperations().getCountArrays();
    }

    @Override
    public void preAnalyze(ContextEl _an) {
        KeyWords keyWords_ = _an.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String methodName_ = getMethodName();
        String className_ = methodName_.trim().substring(newKeyWord_.length());
        int local_ = StringList.getFirstPrintableCharIndex(className_);
        className_ = className_.trim();
        StringMap<String> vars_ = new StringMap<String>();
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        OperationNode m_ = par_.getOperation();
        int nbParentsInfer_ = par_.getNbParentsInfer();
        String typeAff_;
        Block cur_ = _an.getAnalyzing().getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_an);
        } else {
            typeAff_ = tryGetTypeAff(m_);
        }
        if (className_.trim().isEmpty()) {
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (isUndefined(typeAff_, keyWordVar_)) {
                return;
            }
            int chCount_ = getOperations().getValues().size();
            String cp_ = StringExpUtil.getQuickComponentType(typeAff_, chCount_+countArrayDims+nbParentsInfer_);
            if (isNotCorrectDim(cp_)) {
                return;
            }
            typeInfer = cp_;
            return;
        }
        String inferForm_ = AnaTemplates.getInferForm(className_);
        if (inferForm_ == null) {
            return;
        }
        String type_;
        String mName_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(mName_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _an);
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        type_ = ResolvingImportTypes.resolveAccessibleIdTypeWithoutError(_an,newKeyWord_.length()+local_,inferForm_);
        partOffsets_.addAllElts(_an.getAnalyzing().getCurrentParts());
        if (type_.isEmpty()) {
            return;
        }
        int chCount_ = getOperations().getValues().size();
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
                        String format_ = tryParamFormat(filter_,methodInfo_, name_, chCount_+countArrayDims+nbParentsInfer_, type_, vars_, _an);
                        if (format_ == null) {
                            continue;
                        }
                        candidates_.add(format_);
                        newList_.add(methodInfo_);
                    }
                    methodInfos_.set(i,newList_);
                }
                if (candidates_.onlyOneElt()) {
                    String infer_ = candidates_.first();
                    partOffsets.addAllElts(partOffsets_);
                    int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                    int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                    ContextUtil.appendTitleParts(_an,begin_,end_,infer_,partOffsets);
                    typeInfer = infer_;
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
                    String format_ = tryParamFormat(filter_,methodInfo_, name_, chCount_+countArrayDims+nbParentsInfer_, type_, vars_, _an);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.clear();
                methodInfos_.addAllElts(newList_);
                if (candidates_.onlyOneElt()) {
                    String infer_ = candidates_.first();
                    partOffsets.addAllElts(partOffsets_);
                    int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                    int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                    ContextUtil.appendTitleParts(_an,begin_,end_,infer_,partOffsets);
                    typeInfer = infer_;
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
                    String format_ = tryFormat(methodInfo_, indexChild_, chCount_+countArrayDims+nbParentsInfer_, type_, vars_, _an);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.set(i,newList_);
            }
            if (candidates_.onlyOneElt()) {
                String infer_ = candidates_.first();
                partOffsets.addAllElts(partOffsets_);
                int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                ContextUtil.appendTitleParts(_an,begin_,end_,infer_,partOffsets);
                typeInfer = infer_;
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
                String format_ = tryFormat(methodInfo_, indexChild_, chCount_+countArrayDims+nbParentsInfer_, type_, vars_, _an);
                if (format_ == null) {
                    continue;
                }
                candidates_.add(format_);
                newList_.add(methodInfo_);
            }
            methodInfos_.clear();
            methodInfos_.addAllElts(newList_);
            if (candidates_.onlyOneElt()) {
                String infer_ = candidates_.first();
                partOffsets.addAllElts(partOffsets_);
                int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                ContextUtil.appendTitleParts(_an,begin_,end_,infer_,partOffsets);
                typeInfer = infer_;
            }
            return;
        }
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (isUndefined(typeAff_, keyWordVar_)) {
            return;
        }
        String cp_ = StringExpUtil.getQuickComponentType(typeAff_, chCount_+countArrayDims+nbParentsInfer_);
        if (isNotCorrectDim(cp_)) {
            return;
        }
        String infer_ = AnaTemplates.tryInfer(type_,vars_, cp_, _an);
        if (infer_ == null) {
            return;
        }
        partOffsets.addAllElts(partOffsets_);
        int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
        int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
        ContextUtil.appendTitleParts(_an,begin_,end_,infer_,partOffsets);
        typeInfer = infer_;
    }

    @Override
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String m_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        setClassName(page_.getStandards().getAliasObject());
        KeyWords keyWords_ = _conf.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = m_.trim().substring(new_.length());
        if (typeInfer.isEmpty()) {
            int local_ = StringList.getFirstPrintableCharIndex(className_);
            className_ = ResolvingImportTypes.resolveCorrectType(_conf,new_.length()+local_,className_);
            partOffsets.addAllElts(page_.getCurrentParts());
        } else {
            className_ = typeInfer;
        }
        for (OperationNode o: chidren_) {
            int index_ = getPartOffsetsChildren().size();
            IntTreeMap<String> operators_ = getOperations().getOperators();
            CustList<PartOffset> parts_ = new CustList<PartOffset>();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(2*index_), _conf);
            ClassArgumentMatching resCh_ = o.getResultClass();
            if (!resCh_.isNumericInt(_conf)) {
                ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_conf, page_.getStandards().getAliasPrimInteger(), resCh_);
                if (res_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    resCh_.getImplicits().add(cl_);
                    resCh_.setRootNumber(res_.getRootNumber());
                    resCh_.setMemberNumber(res_.getMemberNumber());
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    int i_ = page_.getLocalizer().getCurrentLocationIndex();
                    un_.setIndexFile(i_);
                    un_.setFileName(page_.getLocalizer().getCurrentFileName());
                    //first part child bracket
                    un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                            StringList.join(resCh_.getNames(),"&"));
                    page_.getLocalizer().addError(un_);
                    parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",i_));
                    parts_.add(new PartOffset("</a>",i_+1));
                }
            }
            resCh_.setUnwrapObject(page_.getStandards().getAliasPrimInteger());
            o.cancelArgument();
            getPartOffsetsChildren().add(parts_);
        }
        setClassName(className_);
        setResultClass(new ClassArgumentMatching(StringExpUtil.getPrettyArrayType(className_, chidren_.size()+countArrayDims)));
    }

    public int getCountArrayDims() {
        return countArrayDims;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
