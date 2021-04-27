package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ChoiceFctOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod,AbstractCallFctOperation,SettableElResult {

    private final AnaCallFctContent callFctContent;
    private final AnaArrContent arrContent;
    private AnaTypeFct function;

    private boolean staticMethod;

    private int anc;

    private int delta;
    private int lengthMethod;

    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private boolean trueFalse;
    private String typeInfer = EMPTY_STRING;
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();
    private StandardMethod standardMethod;
    private boolean errLeftValue;

    public ChoiceFctOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new AnaCallFctContent(getOperations().getFctName());
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
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        className_ = ResolvingTypes.resolveCorrectTypeWithoutErrors(lenPref_+loc_,className_.trim(),true,partOffsets_, _page);
        if (!className_.isEmpty()) {
            partOffsets.addAllElts(partOffsets_);
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
        methodInfos = getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMeth_, import_, _page, new ScopeFilter(null, false, false, isLvalue(), _page.getGlobalClass()), getFormattedFilter(_page, this));
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
            className_ = ResolvingTypes.resolveCorrectType(lenPref_+loc_,className_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
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
        ClassMethodId feedBase_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            MethodId mid_ = id_.getConstraints();
            MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), mid_.getKind());
            feedBase_ = new ClassMethodId(idClass_, MethodId.to(static_, trimMeth_, mid_));
            feed_ = new ClassMethodIdAncestor(feedBase_,idMethod_.getAncestor());
        }
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.isOk()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            errLeftValue = true;
            ClassMethodId f_ = getTrueFalse(feedBase_, _page);
            ClassMethodIdReturn clMeth_;
            MethodAccessKind staticAccess_ = isStaticAccess();
            AnaClassArgumentMatching[] argsClass_ = OperationNode.getResultsFromArgs(name_.getPositional());
            clMeth_ = getDeclaredCustTrueFalse(this, staticAccess_,bounds_,trimMeth_,f_, _page, argsClass_);
            if (!clMeth_.isFoundMethod()) {
                setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType()), _page));
                return;
            }
            callFctContent.update(clMeth_);
            function = clMeth_.getPair();
            trueFalse = true;
            MethodId id_ = clMeth_.getRealId();
            staticMethod = true;
            unwrapArgsFct(id_, callFctContent.getNaturalVararg(), callFctContent.getLastType(), name_.getPositional(), _page);
            setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()), _page));
            return;
        }
        ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(this, varargOnly_, isStaticAccess(), bounds_, trimMeth_, import_, varargParam_, name_, _page, new ScopeFilter(feed_, false, false, isLvalue(), _page.getGlobalClass()));
        anc = clMeth_.getAncestor();
        if (!clMeth_.isFoundMethod()) {
            setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType()), _page));
            return;
        }
        if (StringUtil.quickEq(trimMeth_,_page.getKeyWords().getKeyWordNull())) {
            errLeftValue = true;
        }
        callFctContent.update(clMeth_);
        standardMethod = clMeth_.getStandardMethod();
        function = clMeth_.getPair();
        if (clMeth_.isAbstractMethod()) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
            FoundErrorInterpret abs_ = new FoundErrorInterpret();
            abs_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            abs_.setFileName(_page.getLocalizer().getCurrentFileName());
            //method name len
            abs_.buildError(
                    _page.getAnalysisMessages().getAbstractMethodRef(),
                    clMeth_.getRealClass(),
                    clMeth_.getRealId().getSignature(_page));
            _page.getLocalizer().addError(abs_);
            addErr(abs_.getBuiltError());
        }
        MethodId id_ = clMeth_.getRealId();
        staticMethod = id_.getKind() != MethodAccessKind.INSTANCE;
        unwrapArgsFct(id_, callFctContent.getNaturalVararg(), callFctContent.getLastType(), name_.getAll(), _page);
        setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()), _page));
    }

    public AnaTypeFct getFunction() {
        return function;
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

    public CustList<PartOffset> getPartOffsets() {
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
    public MemberId getMemberId() {
        return callFctContent.getMemberId();
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

    @Override
    public void setCatenizeStrings() {
        arrContent.setCatString(true);
    }
}
