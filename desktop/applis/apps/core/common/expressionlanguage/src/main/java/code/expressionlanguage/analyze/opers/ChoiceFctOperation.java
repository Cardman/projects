package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ChoiceFctOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod,AbstractCallFctOperation,SettableElResult {

    private final AnaCallFctContent callFctContent;
    private final AnaArrContent arrContent;

    private boolean staticMethod;

    private int anc;

    private int delta;
    private int lengthMethod;

    private AnaResultPartType partOffsets = new AnaResultPartType();
    private boolean trueFalse;
    private String typeInfer = EMPTY_STRING;
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();
    private StandardMethod standardMethod;
    private boolean errLeftValue;

    public ChoiceFctOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new AnaCallFctContent(_op.getFctName());
        arrContent = new AnaArrContent();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        int off_ = StringUtil.getFirstPrintableCharIndex(callFctContent.getMethodName());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        String trimMeth_;
        boolean import_ = false;
        if (!isIntermediateDottedOperation()) {
            import_ = true;
            setStaticAccess(_page.getStaticContext());
        }
        String className_ = callFctContent.getMethodName().substring(0, callFctContent.getMethodName().lastIndexOf(PAR_RIGHT));
        int lenPref_ = callFctContent.getMethodName().indexOf(PAR_LEFT) + 1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringUtil.getFirstPrintableCharIndex(className_)-off_;
        AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(lenPref_ + loc_, className_.trim(), _page);
        className_ = resType_.getResult();
        if (resType_.isOk()) {
            partOffsets = resType_;
            typeInfer = className_;
        }
        int delta_ = callFctContent.getMethodName().lastIndexOf(PAR_RIGHT)+1;
        String mName_ = callFctContent.getMethodName().substring(delta_);
        trimMeth_ = mName_.trim();
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            return;
        }
        String clCurName_ = className_;
        StringList bounds_ = getBounds(clCurName_, _page);
        methodFound = trimMeth_;
        methodInfos = getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMeth_, import_, _page, new ScopeFilter(null, true, false, isLvalue(),true, _page.getGlobalClass()), getFormattedFilter(_page, this));
        filterByNameReturnType(_page, trimMeth_, methodInfos);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String methodName_ = callFctContent.getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(methodName_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        boolean import_ = false;
        if (!isIntermediateDottedOperation()) {
            import_ = true;
            setStaticAccess(_page.getStaticContext());
        }
        String className_ = methodName_.substring(0, methodName_.lastIndexOf(PAR_RIGHT));
        int lenPref_ = methodName_.indexOf(PAR_LEFT) + 1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringUtil.getFirstPrintableCharIndex(className_)-off_;
        if (typeInfer.isEmpty()) {
            partOffsets = ResolvingTypes.resolveCorrectType(lenPref_ + loc_, className_, _page);
            className_ = partOffsets.getResult(_page);
        } else {
            className_ = typeInfer;
        }
        String clCurName_ = className_;
        StringList bounds_ = getBounds(clCurName_, _page);
        String varargParam_ = getVarargParam(chidren_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        delta = methodName_.lastIndexOf(PAR_RIGHT)+1;
        String mName_ = methodName_.substring(delta);
        delta += StringUtil.getFirstPrintableCharIndex(mName_);
        String trimMeth_ = mName_.trim();
        lengthMethod = trimMeth_.length();
        ClassMethodIdAncestor feed_ = null;
        ClassMethodIdAncestor feedBase_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            AnaGeneType gene_ = idMethod_.getGt();
            String idClass_ = id_.getClassName();
            MethodId mid_ = id_.getConstraints();
            MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), mid_.getKind());
            feedBase_ = new ClassMethodIdAncestor(gene_,new ClassMethodId(idClass_, MethodId.to(static_, trimMeth_, mid_)),0);
            feed_ = new ClassMethodIdAncestor(gene_,feedBase_.getClassMethodId(),idMethod_.getAncestor());
        }
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            errLeftValue = true;
            ClassMethodIdAncestor f_ = getTrueFalse(feedBase_, _page);
            ClassMethodIdReturn clMeth_;
            MethodAccessKind staticAccess_ = isStaticAccess();
            AnaClassArgumentMatching[] argsClass_ = OperationNode.getResultsFromArgs(name_.getAllOps());
            clMeth_ = tryGetDeclaredCustTrueFalse(staticAccess_, bounds_, trimMeth_, f_, argsClass_, _page);
            if (clMeth_ == null) {
                buildErrNotFoundTrueFalse(staticAccess_,trimMeth_,_page,argsClass_);
                setResultClass(voidToObject(new AnaClassArgumentMatching(_page.getAliasObject()), _page));
                return;
            }
            callFctContent.update(clMeth_);
            trueFalse = true;
            staticMethod = true;
            setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()), _page));
            return;
        }
        ClassMethodIdReturn clMeth_ = tryGetDeclaredCustMethod(varargOnly_, isStaticAccess(), bounds_, trimMeth_, import_, varargParam_, name_, _page, new ScopeFilter(feed_, true, false, isLvalue(),true, _page.getGlobalClass()));
        if (clMeth_ == null) {
            ClassMethodIdReturn next_ = tryGetDeclaredCustMethod(varargOnly_, isStaticAccess(), bounds_, trimMeth_, import_, varargParam_, name_, _page, new ScopeFilter(feed_, true, false, isLvalue(),  _page.getGlobalClass()));
            if (next_ != null) {
                callFctContent.update(next_);
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
                FoundErrorInterpret abs_ = new FoundErrorInterpret();
                abs_.setIndexFile(_page);
                abs_.setFile(_page.getCurrentFile());
                //method name len
                abs_.buildError(
                        _page.getAnalysisMessages().getAbstractMethodRef(),
                        next_.getRealClass(),
                        next_.getRealId().getSignature(_page.getDisplayedStrings()));
                _page.getLocalizer().addError(abs_);
                addErr(abs_.getBuiltError());
                setResultClass(voidToObject(new AnaClassArgumentMatching(_page.getAliasObject()), _page));
                return;
            }
            buildErrNotFoundStd(isStaticAccess(), trimMeth_, name_, _page);
            setResultClass(voidToObject(new AnaClassArgumentMatching(_page.getAliasObject()), _page));
            return;
        }
        anc = clMeth_.getAncestor();
        if (StringUtil.quickEq(trimMeth_,_page.getKeyWords().getKeyWordNull())) {
            errLeftValue = true;
        }
        callFctContent.update(clMeth_);
        standardMethod = clMeth_.getStandardMethod();
        MethodId id_ = clMeth_.getRealId();
        staticMethod = id_.getKind() != MethodAccessKind.INSTANCE;
        setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()), _page));
    }

    public ClassMethodId getClassMethodId() {
        return callFctContent.getClassMethodId();
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public AnaCallFctContent getCallFctContent() {
        return callFctContent;
    }

    public int getAnc() {
        return anc;
    }

    public int getDelta() {
        return delta;
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

    public int getLengthMethod() {
        return lengthMethod;
    }

    public boolean isTrueFalse() {
        return trueFalse;
    }

    @Override
    public String getMethodFound() {
        return methodFound;
    }

    @Override
    public CustList<CustList<MethodInfo>> getMethodInfos() {
        return methodInfos;
    }

    @Override
    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public boolean isErrLeftValue() {
        return errLeftValue;
    }
    public AnaArrContent getArrContent() {
        return arrContent;
    }

    @Override
    public void setVariable(boolean _variable) {
        arrContent.setVariable(_variable);
    }

}
