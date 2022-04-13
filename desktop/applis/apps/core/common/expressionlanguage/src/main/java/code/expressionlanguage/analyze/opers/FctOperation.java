package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
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
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FctOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod,AbstractCallFctOperation,SettableElResult {

    private final AnaCallFctContent callFctContent;
    private final AnaArrContent arrContent;
    private boolean staticMethod;

    private boolean staticChoiceMethod;

    private int anc;

    private int lengthMethod;
    private int delta;
    private boolean clonedMethod;
    private boolean trueFalse;
    private String typeInfer = EMPTY_STRING;
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();

    private AnaResultPartType partOffsets = new AnaResultPartType();
    private StandardMethod standardMethod;
    private boolean errLeftValue;
    public FctOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new AnaCallFctContent(getOperations().getFctName());
        arrContent = new AnaArrContent();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        int off_ = StringUtil.getFirstPrintableCharIndex(callFctContent.getMethodName());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        boolean import_ = false;
        AnaClassArgumentMatching clCur_;
        if (isIntermediateDottedOperation()) {
            clCur_ = getPreviousResultClass();
        } else {
            import_ = true;
            clCur_ = new AnaClassArgumentMatching(_page.getGlobalClass());
            setStaticAccess(_page.getStaticContext());
        }
        StringList l_ = clCur_.getNames();
        setDelta(_page);
        String trimMeth_ = callFctContent.getMethodName().trim();
        boolean staticChoiceMethod_ = false;
        boolean accessSuperTypes_ = true;
        boolean baseAccess_ = true;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
            staticChoiceMethod_ = true;
            baseAccess_ = false;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
            staticChoiceMethod_ = true;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
            AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(lenPref_ + loc_, className_.trim(), _page);
            className_ = resType_.getResult();
            if (resType_.isOk()) {
                partOffsets = resType_;
                typeInfer = className_;
            }
            trimMeth_ = trimMeth_.substring(trimMeth_.lastIndexOf(PAR_RIGHT) + 1).trim();
            l_ = getBounds(className_, _page);
            accessSuperTypes_ = false;
        }
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(getBounds(c, _page));
        }
        StringList arrayBounds_ = getArrayBounds(bounds_);
        if (!arrayBounds_.isEmpty()) {
            return;
        }
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            return;
        }
        methodFound = trimMeth_;
        methodInfos = getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMeth_, import_, _page, new ScopeFilter(null, baseAccess_, accessSuperTypes_, isLvalue(),staticChoiceMethod_, _page.getGlobalClass()), getFormattedFilter(_page, this));
        filterByNameReturnType(_page, trimMeth_, methodInfos);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        int off_ = StringUtil.getFirstPrintableCharIndex(callFctContent.getMethodName());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        boolean import_ = false;
        AnaClassArgumentMatching clCur_;
        if (isIntermediateDottedOperation()) {
            clCur_ = getPreviousResultClass();
        } else {
            import_ = true;
            clCur_ = new AnaClassArgumentMatching(_page.getGlobalClass());
            setStaticAccess(_page.getStaticContext());
        }

        CustList<OperationNode> chidren_ = getChildrenNodes();
        setDelta(_page);
        String trimMeth_ = callFctContent.getMethodName().trim();
        String varargParam_ = getVarargParam(chidren_);
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();

        boolean staticChoiceMethod_ = false;
        boolean accessSuperTypes_ = true;
        boolean baseAccess_ = true;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        StringList l_ = clCur_.getNames();
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
            staticChoiceMethod_ = true;
            baseAccess_ = false;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
            staticChoiceMethod_ = true;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            if (typeInfer.isEmpty()) {
                int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
                partOffsets = ResolvingTypes.resolveCorrectType(lenPref_ + loc_, className_, _page);
                className_ = partOffsets.getResult(_page);
            } else {
                className_ = typeInfer;
            }
            Mapping map_ = new Mapping();
            map_.setParam(className_);
            map_.setArg(clCur_);
            StringMap<StringList> mapping_ = _page.getCurrentConstraints().getCurrentConstraints();
            map_.setMapping(mapping_);
            if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setIndexFile(_page);
                cast_.setFile(_page.getCurrentFile());
                //type len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clCur_.getNames(),ExportCst.JOIN_TYPES),
                        className_);
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
            }
            trimMeth_ = trimMeth_.substring(trimMeth_.lastIndexOf(PAR_RIGHT) + 1).trim();
            l_ = getBounds(className_, _page);
            accessSuperTypes_ = false;
        }
        ClassMethodIdAncestor feedBase_ = null;
        ClassMethodIdAncestor feed_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            AnaGeneType gene_ = idMethod_.getGt();
            MethodId mid_ = id_.getConstraints();
            MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), mid_.getKind());
            ClassMethodId classMethodId_ = new ClassMethodId(idClass_, MethodId.to(static_, trimMeth_,mid_));
            feedBase_ = new ClassMethodIdAncestor(gene_,classMethodId_,0);
            feed_ = new ClassMethodIdAncestor(gene_,classMethodId_,idMethod_.getAncestor());
        }
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(getBounds(c, _page));
        }
        StringList arrayBounds_ = getArrayBounds(bounds_);
        if (!arrayBounds_.isEmpty()) {
            if (!StringUtil.quickEq(trimMeth_, _page.getAliasClone())) {
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFile(_page.getCurrentFile());
                undefined_.setIndexFile(_page);
                //trimMeth_ len
                undefined_.buildError(_page.getAnalysisMessages().getArrayCloneOnly(),
                        _page.getAliasClone(),
                        StringUtil.join(arrayBounds_, ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(undefined_);
                addErr(undefined_.getBuiltError());
                return;
            }
            errLeftValue = true;
            clonedMethod = true;
            String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
            MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, trimMeth_, new StringList());
            callFctContent.setClassMethodId(new ClassMethodId(foundClass_, id_));
            setResultClass(new AnaClassArgumentMatching(arrayBounds_));
            return;
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
            staticChoiceMethod = staticChoiceMethod_;
            staticMethod = true;
            setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()), _page));
            return;
        }
        ClassMethodIdReturn clMeth_;
        clMeth_ = tryGetDeclaredCustMethod(varargOnly_, isStaticAccess(), bounds_, trimMeth_, import_, varargParam_, name_, _page, new ScopeFilter(feed_, baseAccess_, accessSuperTypes_, isLvalue(),staticChoiceMethod_, _page.getGlobalClass()));
        if (clMeth_ == null) {
            ClassMethodIdReturn next_ = tryGetDeclaredCustMethod(varargOnly_, isStaticAccess(), bounds_, trimMeth_, import_, varargParam_, name_, _page, new ScopeFilter(feed_, baseAccess_, accessSuperTypes_, isLvalue(),  _page.getGlobalClass()));
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
        staticChoiceMethod = staticChoiceMethod_;
        staticMethod = id_.getKind() != MethodAccessKind.INSTANCE;
        setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()), _page));
    }

    private void setDelta(AnalyzedPageEl _page) {
        String methodName_ = callFctContent.getMethodName();
        String trimMeth_ = methodName_.trim();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        int delta_ = StringUtil.getFirstPrintableCharIndex(methodName_);
        lengthMethod = trimMeth_.length();
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            int after_ = trimMeth_.indexOf('.') + 1;
            delta_ += after_;
            lengthMethod -= after_;
            delta_ += StringUtil.getFirstPrintableCharIndex(trimMeth_.substring(after_));
            lengthMethod -= StringUtil.getFirstPrintableCharIndex(trimMeth_.substring(after_));
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            int after_ = trimMeth_.indexOf('.') + 1;
            delta_ += after_;
            lengthMethod -= after_;
            delta_ += StringUtil.getFirstPrintableCharIndex(trimMeth_.substring(after_));
            lengthMethod -= StringUtil.getFirstPrintableCharIndex(trimMeth_.substring(after_));
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            int lastAfter_ = trimMeth_.lastIndexOf(PAR_RIGHT) + 1;
            delta_ += lastAfter_;
            lengthMethod -= lastAfter_;
            delta_ += StringUtil.getFirstPrintableCharIndex(trimMeth_.substring(lastAfter_));
            lengthMethod -= StringUtil.getFirstPrintableCharIndex(trimMeth_.substring(lastAfter_));
        }
        delta = delta_;
    }
    private static StringList getArrayBounds(StringList _bounds) {
        StringList b_ = new StringList();
        for (String b: _bounds) {
            if (b.startsWith(StringExpUtil.ARR_CLASS)) {
                b_.add(b);
            }
        }
        return b_;
    }

    public ClassMethodId getClassMethodId() {
        return callFctContent.getClassMethodId();
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
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

    public int getLengthMethod() {
        return lengthMethod;
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

    public boolean isClonedMethod() {
        return clonedMethod;
    }

    public boolean isTrueFalse() {
        return trueFalse;
    }

    public CustList<CustList<MethodInfo>> getMethodInfos() {
        return methodInfos;
    }

    public String getMethodFound() {
        return methodFound;
    }

    @Override
    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public AnaArrContent getArrContent() {
        return arrContent;
    }

    @Override
    public void setVariable(boolean _variable) {
        arrContent.setVariable(_variable);
    }

    public boolean isErrLeftValue() {
        return errLeftValue;
    }
}
