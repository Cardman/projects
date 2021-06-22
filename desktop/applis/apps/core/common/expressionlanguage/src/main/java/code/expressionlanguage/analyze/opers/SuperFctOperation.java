package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SuperFctOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod,AbstractCallFctOperation,SettableElResult {

    private final AnaCallFctContent callFctContent;
    private final AnaArrContent arrContent;
    private boolean staticMethod;

    private int anc;
    private int delta;
    private int lengthMethod;
    private boolean trueFalse;
    private boolean errLeftValue;
    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private String typeInfer = EMPTY_STRING;
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();
    private StandardMethod standardMethod;

    public SuperFctOperation(int _index, int _indexChild, MethodOperation _m,
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
        String clCurName_ = className_;
        StringList bounds_ = getBounds(clCurName_, _page);
        int delta_ = callFctContent.getMethodName().lastIndexOf(PAR_RIGHT)+1;
        String mName_ = callFctContent.getMethodName().substring(delta_);
        trimMeth_ = mName_.trim();
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            return;
        }
        methodFound = trimMeth_;
        methodInfos = getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMeth_, import_, _page, new ScopeFilter(null, true, true, isLvalue(), _page.getGlobalClass()), getFormattedFilter(_page, this));
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
        AnaClassArgumentMatching clCur_;
        if (!isIntermediateDottedOperation()) {
            clCur_ = new AnaClassArgumentMatching(_page.getGlobalClass());
            import_ = true;
            setStaticAccess(_page.getStaticContext());
        } else {
            clCur_ = getPreviousResultClass();
        }
        String className_ = methodName_.substring(0, methodName_.lastIndexOf(PAR_RIGHT));
        int lenPref_ = methodName_.indexOf(PAR_LEFT) + 1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringUtil.getFirstPrintableCharIndex(className_)-off_;
        if (typeInfer.isEmpty()) {
            className_ = ResolvingTypes.resolveCorrectType(lenPref_ + loc_, className_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
        } else {
            className_ = typeInfer;
        }
        String clCurName_ = className_;
        StringList bounds_ = getBounds(clCurName_, _page);
        String varargParam_ = getVarargParam(chidren_);
        Mapping map_ = new Mapping();
        map_.setParam(className_);
        map_.setArg(clCur_);
        StringMap<StringList> mapping_ = _page.getCurrentConstraints().getCurrentConstraints();
        map_.setMapping(mapping_);
        if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            cast_.setFileName(_page.getLocalizer().getCurrentFileName());
            //type len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clCur_.getNames(), ExportCst.JOIN_TYPES),
                    className_);
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);

        lengthMethod = methodName_.length();
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
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            errLeftValue = true;
            ClassMethodId f_ = getTrueFalse(feedBase_, _page);
            ClassMethodIdReturn clMeth_;
            MethodAccessKind staticAccess_ = isStaticAccess();
            AnaClassArgumentMatching[] argsClass_ = OperationNode.getResultsFromArgs(name_.getAll());
            clMeth_ = tryGetDeclaredCustTrueFalse(staticAccess_, bounds_, trimMeth_, f_, argsClass_, _page);
            if (clMeth_ == null) {
                buildErrNotFoundTrueFalse(staticAccess_,trimMeth_,_page,argsClass_);
                setResultClass(voidToObject(new AnaClassArgumentMatching(_page.getAliasObject()), _page));
                return;
            }
            callFctContent.update(clMeth_);
            trueFalse = true;
            MethodId id_ = clMeth_.getRealId();
            staticMethod = true;
            unwrapArgsFct(id_, callFctContent.getNaturalVararg(), callFctContent.getLastType(), name_.getAll(), _page);
            setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()), _page));
            return;
        }
        ClassMethodIdReturn clMeth_ = tryGetDeclaredCustMethod(varargOnly_, isStaticAccess(), bounds_, trimMeth_, import_, varargParam_, name_, _page, new ScopeFilter(feed_, true, true, isLvalue(), _page.getGlobalClass()));
        if (clMeth_ == null) {
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

    @Override
    public CustList<CustList<MethodInfo>> getMethodInfos() {
        return methodInfos;
    }

    @Override
    public String getMethodFound() {
        return methodFound;
    }

    public boolean isTrueFalse() {
        return trueFalse;
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
