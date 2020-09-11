package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class FctOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod,AbstractCallFctOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private boolean staticChoiceMethod;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

    private int anc;

    private int lengthMethod;
    private int delta;
    private boolean clonedMethod;
    private boolean trueFalse;
    private String typeInfer = EMPTY_STRING;
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private StandardMethod standardMethod;
    private int rootNumber = -1;
    private int memberNumber = -1;
    public FctOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void preAnalyze(ContextEl _an) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _an);
        boolean import_ = false;
        ClassArgumentMatching clCur_;
        if (isIntermediateDottedOperation()) {
            clCur_ = getPreviousResultClass();
        } else {
            import_ = true;
            clCur_ = new ClassArgumentMatching(_an.getAnalyzing().getGlobalClass());
            setStaticAccess(_an.getAnalyzing().getStaticContext());
        }
        StringList l_ = clCur_.getNames();
        setDelta(_an);
        String trimMeth_ = methodName.trim();
        boolean accessSuperTypes_ = true;
        boolean accessFromSuper_ = false;
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
            accessFromSuper_ = true;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            int loc_ = StringList.getFirstPrintableCharIndex(className_);
            CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
            className_ = ResolvingImportTypes.resolveCorrectTypeWithoutErrors(_an,lenPref_+loc_,className_,true,partOffsets_);
            if (!className_.isEmpty()) {
                partOffsets.addAllElts(partOffsets_);
                typeInfer = className_;
            }
            trimMeth_ = trimMeth_.substring(trimMeth_.lastIndexOf(PAR_RIGHT) + 1).trim();
            l_ = getBounds(className_, _an);
            accessSuperTypes_ = false;
        }
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(getBounds(c, _an));
        }
        StringList arrayBounds_ = getArrayBounds(bounds_);
        if (!arrayBounds_.isEmpty()) {
            return;
        }
        if (isTrueFalseKeyWord(_an, trimMeth_)) {
            return;
        }
        methodFound = trimMeth_;
        methodInfos = getDeclaredCustMethodByType(_an,isStaticAccess(), accessFromSuper_,accessSuperTypes_,bounds_, trimMeth_, import_,null);
        boolean apply_ = applyMatching();
        filterByNameReturnType(_an, trimMeth_, apply_, methodInfos);
    }

    @Override
    public void analyze(ContextEl _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        boolean import_ = false;
        ClassArgumentMatching clCur_;
        if (isIntermediateDottedOperation()) {
            clCur_ = getPreviousResultClass();
        } else {
            import_ = true;
            clCur_ = new ClassArgumentMatching(page_.getGlobalClass());
            setStaticAccess(page_.getStaticContext());
        }

        StringList l_ = clCur_.getNames();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setDelta(_conf);
        String trimMeth_ = methodName.trim();
        String varargParam_ = getVarargParam(chidren_);
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();

        boolean staticChoiceMethod_ = false;
        boolean accessSuperTypes_ = true;
        boolean accessFromSuper_ = false;
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
            staticChoiceMethod_ = true;
            accessFromSuper_ = true;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
            staticChoiceMethod_ = true;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            if (typeInfer.isEmpty()) {
                int loc_ = StringList.getFirstPrintableCharIndex(className_);
                className_ = ResolvingImportTypes.resolveCorrectType(_conf,lenPref_+loc_,className_);
                partOffsets.addAllElts(page_.getCurrentParts());
            } else {
                className_ = typeInfer;
            }
            Mapping map_ = new Mapping();
            map_.setParam(className_);
            map_.setArg(clCur_);
            StringMap<StringList> mapping_ = page_.getCurrentConstraints().getCurrentConstraints();
            map_.setMapping(mapping_);
            if (!AnaTemplates.isCorrectOrNumbers(map_, _conf)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                //type len
                cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clCur_.getNames(),"&"),
                        className_);
                page_.getLocalizer().addError(cast_);
                getErrs().add(cast_.getBuiltError());
            }
            trimMeth_ = trimMeth_.substring(trimMeth_.lastIndexOf(PAR_RIGHT) + 1).trim();
            l_ = getBounds(className_, _conf);
            accessSuperTypes_ = false;
        }
        ClassMethodId feedBase_ = null;
        ClassMethodIdAncestor feed_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            MethodId mid_ = id_.getConstraints();
            boolean vararg_ = mid_.isVararg();
            StringList params_ = mid_.getParametersTypes();
            MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), mid_.getKind());
            ClassMethodId classMethodId_ = new ClassMethodId(idClass_, new MethodId(static_, trimMeth_, params_, vararg_));
            feedBase_ = classMethodId_;
            feed_ = new ClassMethodIdAncestor(classMethodId_,idMethod_.getAncestor());
        }
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(getBounds(c, _conf));
        }
        StringList arrayBounds_ = getArrayBounds(bounds_);
        if (!arrayBounds_.isEmpty()) {
            if (!StringList.quickEq(trimMeth_, stds_.getAliasClone())) {
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFileName(page_.getLocalizer().getCurrentFileName());
                undefined_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //trimMeth_ len
                undefined_.buildError(_conf.getAnalysisMessages().getArrayCloneOnly(),
                        stds_.getAliasClone(),
                        StringList.join(arrayBounds_,"&"));
                page_.getLocalizer().addError(undefined_);
                getErrs().add(undefined_.getBuiltError());
                return;
            }
            clonedMethod = true;
            String foundClass_ = StringExpUtil.getPrettyArrayType(stds_.getAliasObject());
            MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, trimMeth_, new StringList());
            classMethodId = new ClassMethodId(foundClass_, id_);
            setResultClass(new ClassArgumentMatching(arrayBounds_));
            Argument arg_ = getPreviousArgument();
            checkNull(arg_,_conf);
            return;
        }
        NameParametersFilter name_ = buildFilter(_conf);
        if (!name_.isOk()) {
            setResultClass(new ClassArgumentMatching(page_.getStandards().getAliasObject()));
            return;
        }
        if (isTrueFalseKeyWord(_conf, trimMeth_)) {
            ClassMethodId f_ = getTrueFalse(_conf, feedBase_);
            ClassMethodIdReturn clMeth_;
            MethodAccessKind staticAccess_ = isStaticAccess();
            ClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(name_.getPositional());
            clMeth_ = getDeclaredCustTrueFalse(this,_conf, staticAccess_,bounds_,trimMeth_,f_, argsClass_);
            if (!clMeth_.isFoundMethod()) {
                setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()),_conf));
                return;
            }
            rootNumber = clMeth_.getRootNumber();
            memberNumber = clMeth_.getMemberNumber();
            trueFalse = true;
            String foundClass_ = clMeth_.getRealClass();
            MethodId id_ = clMeth_.getRealId();
            classMethodId = new ClassMethodId(foundClass_, id_);
            MethodId realId_ = clMeth_.getRealId();
            staticChoiceMethod = staticChoiceMethod_;
            staticMethod = true;
            unwrapArgsFct(chidren_, realId_, naturalVararg, lastType, name_.getPositional(), _conf);
            setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()),_conf));
            return;
        }
        ClassMethodIdReturn clMeth_;
        clMeth_ = getDeclaredCustMethod(this,_conf, varargOnly_, isStaticAccess(), bounds_, trimMeth_, accessSuperTypes_, accessFromSuper_, import_, feed_, varargParam_, name_);
        anc = clMeth_.getAncestor();
        if (!clMeth_.isFoundMethod()) {
            setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()),_conf));
            return;
        }
        standardMethod = clMeth_.getStandardMethod();
        rootNumber = clMeth_.getRootNumber();
        memberNumber = clMeth_.getMemberNumber();
        if (staticChoiceMethod_) {
            if (clMeth_.isAbstractMethod()) {
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
                FoundErrorInterpret abs_ = new FoundErrorInterpret();
                abs_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                abs_.setFileName(page_.getLocalizer().getCurrentFileName());
                //method name len
                abs_.buildError(
                        _conf.getAnalysisMessages().getAbstractMethodRef(),
                        clMeth_.getRealClass(),
                        clMeth_.getRealId().getSignature(page_));
                page_.getLocalizer().addError(abs_);
                getErrs().add(abs_.getBuiltError());
            }
        }
        String foundClass_ = clMeth_.getRealClass();
        MethodId id_ = clMeth_.getRealId();
        if (id_.getKind() != MethodAccessKind.STATIC_CALL) {
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        }
        classMethodId = new ClassMethodId(foundClass_, id_);
        MethodId realId_ = clMeth_.getRealId();
        if (clMeth_.isVarArgToCall()) {
            StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        staticChoiceMethod = staticChoiceMethod_;
        staticMethod = id_.getKind() != MethodAccessKind.INSTANCE;
        unwrapArgsFct(chidren_, realId_, naturalVararg, lastType, name_.getAll(), _conf);
        setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()),_conf));
        if (isIntermediateDottedOperation() && !staticMethod) {
            Argument arg_ = getPreviousArgument();
            checkNull(arg_,_conf);
        }
    }

    private void setDelta(ContextEl _conf) {
        String trimMeth_ = methodName.trim();
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        int delta_ = StringList.getFirstPrintableCharIndex(methodName);
        lengthMethod = methodName.length();
        int deltaEnd_ = lengthMethod-StringList.getLastPrintableCharIndex(methodName)-1;
        lengthMethod -= delta_;
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            int after_ = trimMeth_.indexOf('.') + 1;
            delta_ += after_;
            lengthMethod -= after_;
            delta_ += StringList.getFirstPrintableCharIndex(trimMeth_.substring(after_));
            lengthMethod -= StringList.getFirstPrintableCharIndex(trimMeth_.substring(after_));
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            int after_ = trimMeth_.indexOf('.') + 1;
            delta_ += after_;
            lengthMethod -= after_;
            delta_ += StringList.getFirstPrintableCharIndex(trimMeth_.substring(after_));
            lengthMethod -= StringList.getFirstPrintableCharIndex(trimMeth_.substring(after_));
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            int lastAfter_ = trimMeth_.lastIndexOf(PAR_RIGHT) + 1;
            delta_ += lastAfter_;
            lengthMethod -= lastAfter_;
            delta_ += StringList.getFirstPrintableCharIndex(trimMeth_.substring(lastAfter_));
            lengthMethod -= StringList.getFirstPrintableCharIndex(trimMeth_.substring(lastAfter_));
        }
        lengthMethod -= deltaEnd_;
        delta = delta_;
    }
    private static StringList getArrayBounds(StringList _bounds) {
        StringList b_ = new StringList();
        for (String b: _bounds) {
            if (b.startsWith(PrimitiveTypeUtil.ARR_CLASS)) {
                b_.add(b);
            }
        }
        return b_;
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        tryGetArg(this, getPreviousArgument(),_conf, classMethodId, naturalVararg, lastType);
    }

    public static void tryGetArg(FctOperation _current, Argument _pr,ContextEl _conf,
                                 ClassMethodId _classMethodId, int _naturalVararg, String _lastType) {
        CustList<OperationNode> chidren_ = _current.getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        if (_classMethodId == null) {
            return;
        }
        Argument previous_;
        previous_ = _pr;
        Struct str_;
        MethodId id_ = _classMethodId.getConstraints();
        if (!id_.isStaticMethod()) {
            if (previous_ == null || previous_.isNull()) {
                return;
            }
            str_ = previous_.getStruct();
        } else if (previous_ != null) {
            str_ = previous_.getStruct();
        } else {
            str_ = NullStruct.NULL_VALUE;
        }
        CustList<Argument> firstArgs_ = quickListArguments(chidren_, _naturalVararg, _lastType, arguments_);
        Struct out_ = AnaApplyCoreMethodUtil.invokeAnalyzisStdMethod(_conf, _classMethodId, str_, Argument.toArgArray(firstArgs_));
        if (out_ == null) {
            return;
        }
        Argument arg_ = new Argument(out_);
        _current.setSimpleArgumentAna(arg_, _conf);
    }
    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
    }

    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
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

    public CustList<PartOffset> getPartOffsets() {
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

    @Override
    public int getRootNumber() {
        return rootNumber;
    }

    @Override
    public int getMemberNumber() {
        return memberNumber;
    }

}
