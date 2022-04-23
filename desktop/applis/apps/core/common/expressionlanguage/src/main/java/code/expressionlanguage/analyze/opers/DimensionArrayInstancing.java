package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.ReturnMethod;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DimensionArrayInstancing extends
        AbstractArrayInstancingOperation implements PreAnalyzableOperation {
    private final int countArrayDims;
    private String typeInfer = EMPTY_STRING;
    private ResolvedInstance resolvedInstance = new ResolvedInstance();

    public DimensionArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        countArrayDims = _op.getCountArrays();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String methodName_ = getMethodName();
        String className_ = methodName_.trim().substring(newKeyWord_.length());
        int local_ = StringUtil.getFirstPrintableCharIndex(className_);
        className_ = className_.trim();
        StringMap<String> vars_ = new StringMap<String>();
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        OperationNode m_ = par_.getOperation();
        int nbParentsInfer_ = par_.getNbParentsInfer();
        String typeAff_;
        AbsBk cur_ = _page.getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_page);
        } else {
            typeAff_ = tryGetTypeAff(m_, par_.getOperationChild().getIndexChild());
        }
        if (className_.trim().isEmpty()) {
            int chCount_ = getChildren().size();
            boolean list_ = false;
            if (m_ instanceof ArgumentListInstancing){
                m_ = m_.getParent().getParent();
                list_ = true;
            }
            if (m_ instanceof NamedArgumentOperation){
                NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
                String inferRecord_ = n_.infer();
                if (!inferRecord_.isEmpty()) {
                    String format_ = tryGetRecordDim(inferRecord_, chCount_ + countArrayDims + nbParentsInfer_);
                    if (format_ != null) {
                        typeInfer = format_;
                    }
                    return;
                }
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
                            String format_ = tryParamFormatEmp(filter_,methodInfo_, name_, chCount_+countArrayDims+nbParentsInfer_);
                            if (format_ == null) {
                                continue;
                            }
                            candidates_.add(format_);
                            newList_.add(methodInfo_);
                        }
                        methodInfos_.set(i,newList_);
                    }
                    if (candidates_.onlyOneElt()) {
                        typeInfer = candidates_.first();
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
                        String format_ = tryParamFormatEmp(filter_,methodInfo_, name_, chCount_+countArrayDims+nbParentsInfer_);
                        if (format_ == null) {
                            continue;
                        }
                        candidates_.add(format_);
                        newList_.add(methodInfo_);
                    }
                    methodInfos_.clear();
                    methodInfos_.addAllElts(newList_);
                    if (candidates_.onlyOneElt()) {
                        typeInfer = candidates_.first();
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
                        String format_ = tryFormatEmp(methodInfo_, indexChild_, chCount_+countArrayDims+nbParentsInfer_);
                        if (format_ == null) {
                            continue;
                        }
                        candidates_.add(format_);
                        newList_.add(methodInfo_);
                    }
                    methodInfos_.set(i,newList_);
                }
                if (candidates_.onlyOneElt()) {
                    typeInfer = candidates_.first();
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
                    String format_ = tryFormatEmp(methodInfo_, indexChild_, chCount_+countArrayDims+nbParentsInfer_);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.clear();
                methodInfos_.addAllElts(newList_);
                if (candidates_.onlyOneElt()) {
                    typeInfer = candidates_.first();
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
            typeInfer = cp_;
            return;
        }
        String inferForm_ = AnaTemplates.getInferForm(className_);
        if (inferForm_ == null) {
            return;
        }
        String type_;
        String mName_ = getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(mName_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        AnaResultPartType result_ = ResolvingTypes.resolveAccessibleIdTypeWithoutError(newKeyWord_.length() + local_, inferForm_, _page);
        type_ = result_.getResult();
        if (type_.isEmpty()) {
            return;
        }
        resolvedInstance = new ResolvedInstance(result_);
        int chCount_ = getChildren().size();
        boolean list_ = false;
        if (m_ instanceof ArgumentListInstancing){
            m_ = m_.getParent().getParent();
            list_ = true;
        }
        if (m_ instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
            String inferRecord_ = n_.infer();
            if (!inferRecord_.isEmpty()) {
                String format_ = tryFormatArrRec(inferRecord_, chCount_ + countArrayDims + nbParentsInfer_, type_, vars_, _page);
                if (format_ != null) {
                    int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                    int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                    resolvedInstance = new ResolvedInstance(resolvedInstance, _page,begin_, end_,format_);
                    typeInfer = format_;
                }
                return;
            }
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
                        String format_ = tryParamFormatArr(filter_,methodInfo_, name_, chCount_+countArrayDims+nbParentsInfer_, type_, vars_, _page);
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
                    int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                    int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                    resolvedInstance = new ResolvedInstance(resolvedInstance, _page,begin_, end_,infer_);
                    typeInfer = infer_;
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
                    String format_ = tryParamFormatArr(filter_,methodInfo_, name_, chCount_+countArrayDims+nbParentsInfer_, type_, vars_, _page);
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
                    int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                    int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                    resolvedInstance = new ResolvedInstance(resolvedInstance, _page,begin_, end_,infer_);
                    typeInfer = infer_;
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
                    String format_ = tryFormatArr(methodInfo_, indexChild_, chCount_+countArrayDims+nbParentsInfer_, type_, vars_, _page);
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
                int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                resolvedInstance = new ResolvedInstance(resolvedInstance, _page,begin_, end_,infer_);
                typeInfer = infer_;
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
                String format_ = tryFormatArr(methodInfo_, indexChild_, chCount_+countArrayDims+nbParentsInfer_, type_, vars_, _page);
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
                int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                resolvedInstance = new ResolvedInstance(resolvedInstance, _page,begin_, end_,infer_);
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
        String infer_ = tryInferOrImplicitArr(type_,vars_, _page, cp_);
        if (infer_ == null) {
            return;
        }
        int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
        int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
        resolvedInstance = new ResolvedInstance(resolvedInstance, _page,begin_, end_,infer_);
        typeInfer = infer_;
    }
    private static String tryParamFormatEmp(NameParametersFilter _filter, Parametrable _param, String _name, int _nbParentsInfer) {
        if (!isValidNameIndex(_filter,_param,_name)) {
            return null;
        }
        int ind_ = StringUtil.indexOf(_param.getParametersNames(), _name);
        return tryFormatEmp(_param, ind_, _nbParentsInfer);
    }
    private static String tryFormatEmp(Parametrable _param, int _indexChild, int _nbParentsInfer) {
        return tryGetParamDim(_param,_indexChild,_nbParentsInfer);
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
            int local_ = StringUtil.getFirstPrintableCharIndex(className_);
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(new_.length() + local_, className_, _page);
            className_ = result_.getResult(_page);
            resolvedInstance = new ResolvedInstance(result_);
        } else {
            className_ = typeInfer;
        }
        for (OperationNode o: chidren_) {
            int index_ = getPartOffsetsChildren().size();
            StrTypes operators_ = getOperators();
            InfoErrorDto parts_ = new InfoErrorDto("");
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(2*index_), _page);
            AnaClassArgumentMatching resCh_ = o.getResultClass();
            if (!resCh_.isNumericInt(_page)) {
                ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimInteger(), resCh_, _page);
                if (res_ != null) {
                    resCh_.implicitInfos(res_);
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setIndexFile(_page);
                    un_.setFile(_page.getCurrentFile());
                    //first part child bracket
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(resCh_.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(un_);
                    parts_=new InfoErrorDto(un_,_page,1);
                }
            }
            resCh_.setUnwrapObjectNb(PrimitiveTypes.INT_WRAP);
            getPartOffsetsChildren().add(parts_);
        }
        setClassName(className_);
        setResultClass(new AnaClassArgumentMatching(StringExpUtil.getPrettyArrayType(className_, chidren_.size()+countArrayDims)));
    }

    public String getTypeInfer() {
        return typeInfer;
    }

    public int getCountArrayDims() {
        return countArrayDims;
    }

    public ResolvedInstance getPartOffsets() {
        return resolvedInstance;
    }
}
