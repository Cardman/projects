package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.InvokingConstructor;
import code.expressionlanguage.InvokingMethod;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.AbstractMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessConstructor;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.UndefinedConstructorError;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.methods.util.VarargError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class FctOperation extends InvokingOperation {

    private String methodName;

    private ConstructorId constId;

    private ClassMethodId classMethodId;
    private MethodId realId;

    private boolean staticMethod;

    private boolean superConstructorCall;

    private boolean otherConstructorClass;

    private boolean staticChoiceMethod;

    private boolean correctTemplate = true;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

    public FctOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void analyze(Analyzable _conf,
            String _fieldName) {
        analyzeCommon(_conf);
    }

    void analyzeCommon(Analyzable _conf) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        int varargOnly_ = lookOnlyForVarArg();
        LgNames stds_ = _conf.getStandards();
        String stringType_ = stds_.getAliasString();
        if (StringList.quickEq(trimMeth_,prefixFunction(VAR_ARG))) {
            setVararg(true);
            MethodOperation m_ = getParent();
            if (!(m_ instanceof InvokingOperation)) {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(trimMeth_);
                _conf.getClasses().getErrorsDet().add(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            InvokingOperation parent_ = (InvokingOperation) m_;
            if (!parent_.isCallMethodCtor()) {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(trimMeth_);
                _conf.getClasses().getErrorsDet().add(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            if (!isFirstChild()) {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(trimMeth_);
                _conf.getClasses().getErrorsDet().add(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            if (chidren_.size() != CustList.ONE_ELEMENT) {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(trimMeth_);
                _conf.getClasses().getErrorsDet().add(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            if (!chidren_.first().getResultClass().matchClass(stringType_)) {
                setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl()+1, _conf);
                ClassArgumentMatching cl_ = chidren_.first().getResultClass();
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setRc(_conf.getCurrentLocation());
                un_.setFileName(_conf.getCurrentFileName());
                un_.setExpectedResult(stringType_);
                un_.setOperands(new StringList(cl_.getName()));
                _conf.getClasses().getErrorsDet().add(un_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            if (chidren_.first().getArgument() == null) {
                setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl()+1, _conf);
                BadImplicitCast bad_ = new BadImplicitCast();
                Mapping map_ = new Mapping();
                map_.setArg(EMPTY_STRING);
                map_.setParam(stringType_);
                bad_.setMapping(map_);
                bad_.setFileName(_conf.getCurrentFileName());
                bad_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(bad_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            String str_ = (String) chidren_.first().getArgument().getObject();
            if (str_ == null) {
                setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl()+1, _conf);
                BadImplicitCast bad_ = new BadImplicitCast();
                Mapping map_ = new Mapping();
                map_.setArg(EMPTY_STRING);
                map_.setParam(stringType_);
                bad_.setMapping(map_);
                bad_.setFileName(_conf.getCurrentFileName());
                bad_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(bad_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            str_ = StringList.removeAllSpaces(str_);
            if (!checkCorrect(_conf, str_, true, chidren_.first().getIndexInEl()+1)) {
                str_ = stds_.getAliasObject();
            }
            setResultClass(new ClassArgumentMatching(str_));
            setSimpleArgument(new Argument());
            return;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(FIRST_OPT))) {
            setFirstOptArg(true);
            MethodOperation m_ = getParent();
            if (!(m_ instanceof InvokingOperation)) {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(trimMeth_);
                _conf.getClasses().getErrorsDet().add(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            InvokingOperation parent_ = (InvokingOperation)m_;
            if (!parent_.isCallMethodCtor()) {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(trimMeth_);
                _conf.getClasses().getErrorsDet().add(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            if (isFirstChild()) {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(trimMeth_);
                _conf.getClasses().getErrorsDet().add(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            if (chidren_.size() != CustList.ONE_ELEMENT) {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(trimMeth_);
                _conf.getClasses().getErrorsDet().add(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            setResultClass(chidren_.first().getResultClass());
            return;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(CURRENT))) {
            //validate calling of constructors of the current class
            String clCurName_ = _conf.getGlobalClass();
            otherConstructorClass = true;
            CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
            ConstrustorIdVarArg ctorRes_;
            ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, new ClassArgumentMatching(clCurName_), ClassArgumentMatching.toArgArray(firstArgs_));
            if (ctorRes_ != null) {
                constId = ctorRes_.getRealId();
                if (ctorRes_.isVarArgToCall()) {
                    naturalVararg = constId.getParametersTypes().size() - 1;
                    lastType = constId.getParametersTypes().last();
                }
                if (!chidren_.isEmpty() && chidren_.first().isVararg()) {
                    int i_ = CustList.FIRST_INDEX;
                    for (OperationNode o: chidren_) {
                        if (o.isVararg()) {
                            i_++;
                            continue;
                        }
                        if (o.isFirstOptArg()) {
                            break;
                        }
                        String param_ = constId.getParametersTypes().get(i_-1);
                        if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                            o.getResultClass().setUnwrapObject(param_);
                        }
                        i_++;
                    }
                } else if (naturalVararg > -1) {
                    int lenCh_ = firstArgs_.size();
                    for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                        ClassArgumentMatching a_ = firstArgs_.get(i);
                        if (i >= naturalVararg) {
                            if (PrimitiveTypeUtil.isPrimitive(lastType, _conf)) {
                                a_.setUnwrapObject(lastType);
                            }
                        } else {
                            String param_ = constId.getParametersTypes().get(i);
                            if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                                a_.setUnwrapObject(param_);
                            }
                        }
                    }
                } else {
                    int lenCh_ = firstArgs_.size();
                    for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                        ClassArgumentMatching a_ = firstArgs_.get(i);
                        String param_ = constId.getParametersTypes().get(i);
                        if (i + 1 == lenCh_ && constId.isVararg()) {
                            param_ = PrimitiveTypeUtil.getPrettyArrayType(param_);
                        }
                        if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                            a_.setUnwrapObject(param_);
                        }
                    }
                }
                setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
                return;
            }
            StringList cl_ = new StringList();
            for (ClassArgumentMatching c: firstArgs_) {
                cl_.add(c.getName());
            }
            ConstructorId constId_ = new ConstructorId(clCurName_, cl_, false);
            UndefinedConstructorError und_ = new UndefinedConstructorError();
            und_.setId(constId_);
            und_.setClassName(clCurName_);
            und_.setRc(_conf.getCurrentLocation());
            und_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().getErrorsDet().add(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
            return;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(SUPER_ACCESS))) {
            //validate calling of super constructors
            String clCurName_ = _conf.getGlobalClass();
            String base_ = StringList.getAllTypes(clCurName_).first();
            superConstructorCall = true;
            CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
            UniqueRootedBlock unique_ =(UniqueRootedBlock) classes_.getClassBody(base_);
            String superClass_ = Templates.format(clCurName_, unique_.getGenericSuperClass(_conf), _conf);
            ConstrustorIdVarArg ctorRes_;
            ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, new ClassArgumentMatching(superClass_), ClassArgumentMatching.toArgArray(firstArgs_));
            if (ctorRes_ != null) {
                constId = ctorRes_.getRealId();
                CustList<ConstructorBlock> ctors_ = classes_.getConstructorBodiesById(superClass_, constId);
                if (!ctors_.isEmpty() && !Classes.canAccess(clCurName_, ctors_.first(), _conf)) {
                    ConstructorBlock ctr_ = ctors_.first();
                    BadAccessConstructor badAccess_ = new BadAccessConstructor();
                    badAccess_.setId(ctr_.getId());
                    badAccess_.setFileName(_conf.getCurrentFileName());
                    badAccess_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().getErrorsDet().add(badAccess_);
                }
                if (ctorRes_.isVarArgToCall()) {
                    naturalVararg = constId.getParametersTypes().size() - 1;
                    lastType = constId.getParametersTypes().last();
                }
                if (!chidren_.isEmpty() && chidren_.first().isVararg()) {
                    int i_ = CustList.FIRST_INDEX;
                    for (OperationNode o: chidren_) {
                        if (o.isVararg()) {
                            i_++;
                            continue;
                        }
                        if (o.isFirstOptArg()) {
                            break;
                        }
                        String param_ = constId.getParametersTypes().get(i_-1);
                        if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                            o.getResultClass().setUnwrapObject(param_);
                        }
                        i_++;
                    }
                } else if (naturalVararg > -1) {
                    int lenCh_ = firstArgs_.size();
                    for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                        ClassArgumentMatching a_ = firstArgs_.get(i);
                        if (i >= naturalVararg) {
                            if (PrimitiveTypeUtil.isPrimitive(lastType, _conf)) {
                                a_.setUnwrapObject(lastType);
                            }
                        } else {
                            String param_ = constId.getParametersTypes().get(i);
                            if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                                a_.setUnwrapObject(param_);
                            }
                        }
                    }
                } else {
                    int lenCh_ = firstArgs_.size();
                    for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                        ClassArgumentMatching a_ = firstArgs_.get(i);
                        String param_ = constId.getParametersTypes().get(i);
                        if (i + 1 == lenCh_ && constId.isVararg()) {
                            param_ = PrimitiveTypeUtil.getPrettyArrayType(param_);
                        }
                        if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                            a_.setUnwrapObject(param_);
                        }
                    }
                }
                setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
                return;
            }
            StringList cl_ = new StringList();
            for (ClassArgumentMatching c: firstArgs_) {
                cl_.add(c.getName());
            }
            ConstructorId constId_ = new ConstructorId(superClass_, cl_, false);
            UndefinedConstructorError und_ = new UndefinedConstructorError();
            und_.setId(constId_);
            und_.setClassName(superClass_);
            und_.setRc(_conf.getCurrentLocation());
            und_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().getErrorsDet().add(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
            return;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(INSTANCEOF))) {
            if (chidren_.size() == 2) {
                if (!chidren_.first().getResultClass().matchClass(stringType_)) {
                    setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl()+1, _conf);
                    ClassArgumentMatching cl_ = chidren_.first().getResultClass();
                    UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                    un_.setRc(_conf.getCurrentLocation());
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setExpectedResult(stringType_);
                    un_.setOperands(new StringList(cl_.getName()));
                    _conf.getClasses().getErrorsDet().add(un_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
                    return;
                }
                if (chidren_.first().getArgument() == null) {
                    setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl()+1, _conf);
                    BadImplicitCast bad_ = new BadImplicitCast();
                    Mapping map_ = new Mapping();
                    map_.setArg(EMPTY_STRING);
                    map_.setParam(stringType_);
                    bad_.setMapping(map_);
                    bad_.setFileName(_conf.getCurrentFileName());
                    bad_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().getErrorsDet().add(bad_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
                    return;
                }
                String str_ = (String) chidren_.first().getArgument() .getObject();
                if (str_ == null) {
                    setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl()+1, _conf);
                    BadImplicitCast bad_ = new BadImplicitCast();
                    Mapping map_ = new Mapping();
                    map_.setArg(EMPTY_STRING);
                    map_.setParam(stringType_);
                    bad_.setMapping(map_);
                    bad_.setFileName(_conf.getCurrentFileName());
                    bad_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().getErrorsDet().add(bad_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
                    return;
                }
                str_ = StringList.removeAllSpaces(str_);
                if (str_.contains(Templates.TEMPLATE_BEGIN)) {
                    checkCorrect(_conf, str_, true, chidren_.first().getIndexInEl()+1);
                } else {
                    if (!checkExistBase(_conf, !isStaticBlock(), str_, true, chidren_.first().getIndexInEl()+1)) {
                        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
                        return;
                    }
                    if (!str_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                        correctTemplate = Templates.correctNbParameters(str_, _conf);
                    }
                }
                setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
                return;
            }
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badNb_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            return;
        }
        ClassArgumentMatching clCur_;
        if (isIntermediateDottedOperation()) {
            clCur_ = getPreviousResultClass();
        } else {
            clCur_ = new ClassArgumentMatching(_conf.getGlobalClass());
            setStaticAccess(_conf.isStaticContext());
        }
        String clCurName_;

        if (clCur_ == null || clCur_.getName() == null) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(static_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        clCurName_ = clCur_.getName();
        if (hasVoidPrevious(clCurName_, _conf)) {
            return;
        }
        StringList bounds_ = getBounds(clCurName_, _conf);
        analyzeCustomClass(_conf, bounds_);
//        if (firstArgs_.isEmpty()) {
//            if (StringList.quickEq(trimMeth_, GET_CLASS)) {
//                setResultClass(new ClassArgumentMatching(NativeTypeUtil.getPrettyType(ClassMetaInfo.class)));
//                return;
//            }
//        }
    }

    private void analyzeCustomClass(Analyzable _conf, StringList _subTypes) {
        LgNames stds_ = _conf.getStandards();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        int varargOnly_ = lookOnlyForVarArg();
        if (hasVoidArguments(chidren_, firstArgs_, off_, _conf)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);

        boolean staticChoiceMethod_ = false;
        boolean accessFromSuper_ = false;
        if (trimMeth_.startsWith(prefixFunction(StringList.concat(SUPER_ACCESS, String.valueOf(EXTERN_CLASS))))) {
            trimMeth_ = trimMeth_.substring(SUPER_ACCESS.length() + 2);
            staticChoiceMethod_ = true;
            accessFromSuper_ = true;
        } else if (trimMeth_.startsWith(prefixFunction(StringList.concat(CURRENT, String.valueOf(EXTERN_CLASS))))) {
            trimMeth_ = trimMeth_.substring(CURRENT.length() + 2);
            staticChoiceMethod_ = true;
        }
        ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(_conf, varargOnly_, isStaticAccess(), _subTypes, trimMeth_, true, accessFromSuper_, ClassArgumentMatching.toArgArray(firstArgs_));
        if (!clMeth_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
            return;
        }
        if (staticChoiceMethod_) {
            if (clMeth_.isAbstractMethod()) {
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
                AbstractMethod abs_ = new AbstractMethod();
                abs_.setClassName(clMeth_.getRealClass());
                abs_.setSgn(clMeth_.getRealId().getSignature());
                abs_.setRc(_conf.getCurrentLocation());
                abs_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().getErrorsDet().add(abs_);
                setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
                return;
            }
        }
        String foundClass_ = clMeth_.getRealClass();
        foundClass_ = StringList.getAllTypes(foundClass_).first();
        MethodId id_ = clMeth_.getRealId();
        classMethodId = new ClassMethodId(foundClass_, id_);
        realId = clMeth_.getRealId();
        if (clMeth_.isVarArgToCall()) {
            StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        staticChoiceMethod = staticChoiceMethod_;
        staticMethod = clMeth_.isStaticMethod();
        unwrapArgsFct(chidren_, realId, naturalVararg, lastType, firstArgs_, _conf);
        setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        ObjectMap<ClassField,AssignmentBefore> fieldsBefore_ = new ObjectMap<ClassField,AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<ClassField, Assignment> e: fieldsAfter_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsBefore_.put(e.getKey(), b_.assignBefore());
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);
        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        if (children_.isEmpty()) {
            boolean isBool_;
            isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
            for (EntryCust<ClassField, AssignmentBefore> e: vars_.getFieldsBefore().getVal(this).entryList()) {
                AssignmentBefore b_ = e.getValue();
                fieldsAfter_.put(e.getKey(), b_.assignAfter(isBool_));
            }
            for (StringMap<AssignmentBefore> s: vars_.getVariablesBefore().getVal(this)) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore b_ = e.getValue();
                    sm_.put(e.getKey(), b_.assignAfter(isBool_));
                }
                variablesAfter_.add(sm_);
            }
            vars_.getFields().put(this, fieldsAfter_);
            vars_.getVariables().put(this, variablesAfter_);
            return;
        }
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);

        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(isBool_));
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }
    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        return calculateCommon(_nodes, _conf);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = _nodes.getVal(this).getPreviousArgument();
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        ArgumentCall argres_ = getArgument(previous_, arguments_, _conf);
        Argument res_ = argres_.getArgument();
        if (argres_.isInitClass()) {
            _conf.setInitClass(new NotInitializedClass(argres_.getInitClass().getClassName()));
        } else if (argres_.isInvokeConstructor()) {
            InvokingConstructor i_ = argres_.getInvokeConstructor();
            _conf.setCallCtor(new CustomFoundConstructor(i_.getClassName(), i_.getFieldName(), i_.getOrdinal(), i_.getCalled(), i_.getId(), i_.getCurrentObject(), i_.getArguments(), i_.getInstanceStep()));
        } else if (argres_.isInvokeMethod()) {
            InvokingMethod i_ = argres_.getInvokeMethod();
            _conf.setCallMethod(new CustomFoundMethod(i_.getGl(), i_.getClassName(), i_.getId(), i_.getArguments()));
        } else {
            setSimpleArgument(res_, _conf, _nodes);
        }
        return res_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (!_conf.isGearConst()) {
            return;
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        LgNames stds_ = _conf.getStandards();
        String trimMeth_ = methodName.trim();
        if (StringList.quickEq(trimMeth_,prefixFunction(FIRST_OPT))) {
            setSimpleArgumentAna(arguments_.first(), _conf);
            return;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(CAST))) {
            if (chidren_.size() == 2) {
                Argument objArg_ = arguments_.last();
                Argument classArg_ = arguments_.first();
                String paramName_ = (String) classArg_.getObject();
                if (PrimitiveTypeUtil.primitiveTypeNullObject(paramName_, objArg_.getStruct(), _conf.getStandards())) {
                    return;
                }
                if (objArg_.isNull()) {
                    Argument arg_ = new Argument();
                    setSimpleArgumentAna(arg_, _conf);
                    return;
                }
                if (!PrimitiveTypeUtil.isPrimitive(paramName_, _conf)) {
                    if (!StringList.quickEq(paramName_, _conf.getStandards().getAliasString())) {
                        return;
                    }
                }
                Object o_ = objArg_.getObject();
                String argClassName_ = _conf.getStandards().getSimpleStructClassName(o_);
                ClassArgumentMatching resCl_ = getResultClass();
                Argument arg_ = new Argument();
                if (!PrimitiveTypeUtil.isPrimitive(paramName_, _conf)) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(argClassName_);
                    mapping_.setParam(paramName_);
                    if (!Templates.isCorrect(mapping_, _conf)) {
                        return;
                    }
                    arg_.setStruct(objArg_.getStruct());
                } else {
                    if (PrimitiveTypeUtil.getOrderClass(paramName_, _conf) > 0) {
                        if (PrimitiveTypeUtil.getOrderClass(argClassName_, _conf) == 0) {
                            return;
                        }
                        arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, objArg_.getStruct(), _conf));
                    } else {
                        String typeNameArg_ = PrimitiveTypeUtil.toPrimitive(new ClassArgumentMatching(argClassName_), true, _conf).getName();
                        if (!StringList.quickEq(typeNameArg_, stds_.getAliasPrimBoolean())) {
                            return;
                        }
                        arg_.setStruct(objArg_.getStruct());
                    }
                }
                setSimpleArgumentAna(arg_, _conf);
                return;
            }
        }
        if (classMethodId == null) {
            return;
        }
        Argument previous_;
        previous_ = getPreviousArgument();
        if (!classMethodId.getConstraints().isStaticMethod()) {
            if (previous_ == null || previous_.isNull()) {
                return;
            }
        }
        String cl_ = classMethodId.getClassName();
        if (_conf.getClasses().isCustomType(cl_)) {
            return;
        }
        boolean proc_ = false;
        if (PrimitiveTypeUtil.isPrimitiveOrWrapper(cl_, _conf)) {
            proc_ = true;
        } else if (StringList.quickEq(cl_, _conf.getStandards().getAliasString())) {
            proc_ = true;
        } else if (StringList.quickEq(cl_, _conf.getStandards().getAliasMath())) {
            proc_ = true;
        }
        if (!proc_) {
            return;
        }
        if (lastType == null) {
            return;
        }
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        CustList<Argument> firstArgs_ = quickListArguments(chidren_, naturalVararg_, lastType_, arguments_, _conf);
        if (firstArgs_ == null) {
            return;
        }
        ResultErrorStd res_ = LgNames.invokeStdMethod(_conf, naturalVararg > -1, classMethodId, previous_.getStruct(), Argument.toArgArray(firstArgs_));
        if (res_.getResult() == null) {
            return;
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(res_.getResult());
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    public void calculate(ContextEl _conf) {
        calculateCommon(_conf);
    }

    void calculateCommon(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        ArgumentCall argres_ = getArgument(previous_, arguments_, _conf);
        if (argres_.isInitClass()) {
            ProcessMethod.initializeClass(argres_.getInitClass().getClassName(), _conf);
            if (_conf.getException() != null) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf);
        }
        Argument res_;
        if (argres_.isInvokeConstructor()) {
            InvokingConstructor i_ = argres_.getInvokeConstructor();
            res_ = ProcessMethod.instanceArgument(i_.getClassName(), i_.getCurrentObject(), i_.getId(), i_.getArguments(), _conf);
        } else if (argres_.isInvokeMethod()) {
            InvokingMethod i_ = argres_.getInvokeMethod();
            res_ = ProcessMethod.calculateArgument(i_.getGl(), i_.getClassName(), i_.getId(), i_.getArguments(), _conf);
        } else {
            res_ = argres_.getArgument();
        }
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }
    ArgumentCall getArgument(Argument _previous, CustList<Argument> _arguments, ContextEl _conf) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        if (StringList.quickEq(trimMeth_,prefixFunction(FIRST_OPT))) {
            return ArgumentCall.newArgument(_arguments.first());
        }
        LgNames stds_ = _conf.getStandards();
        String null_;
        String cast_;
        null_ = stds_.getAliasNullPe();
        cast_ = stds_.getAliasCast();
        if (constId != null) {
            Argument arg_ = _conf.getLastPage().getGlobalArgument();
            String clCurName_ = arg_.getObjectClassName(_conf);
            String gl_ = _conf.getLastPage().getGlobalClass();
            gl_ = StringList.getAllTypes(gl_).first();
            String base_ = StringList.getAllTypes(gl_).first();
            gl_ = Templates.getFullTypeByBases(clCurName_, gl_, _conf);
            UniqueRootedBlock unique_ =(UniqueRootedBlock) classes_.getClassBody(base_);
            CustList<Argument> firstArgs_;
            String calledCtor_ = base_;
            String calledCtorTemp_ = gl_;
            if (StringList.quickEq(trimMeth_,prefixFunction(CURRENT))) {
                String lastType_ = Templates.format(gl_, lastType, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg, lastType_, _arguments, _conf);
            } else {
                String superClass_ = Templates.format(gl_, unique_.getGenericSuperClass(_conf), _conf);
                String superClassBase_ = StringList.getAllTypes(superClass_).first();
                String lastType_ = Templates.format(superClass_, lastType, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg, lastType_, _arguments, _conf);
                calledCtor_ = superClassBase_;
                calledCtorTemp_ = superClass_;
            }
            StringList params_ = new StringList();
            String classFormat_ = calledCtor_;
            classFormat_ = Templates.getFullTypeByBases(clCurName_, classFormat_, _conf);
            if (classFormat_ == null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                Argument a_ = new Argument();
                return ArgumentCall.newArgument(a_);
            }
            int j_ = 0;
            for (String c: constId.getParametersTypes()) {
                String c_ = c;
                c_ = Templates.format(classFormat_, c_, _conf);
                if (j_ + 1 == constId.getParametersTypes().size() && constId.isVararg()) {
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                j_++;
            }
            int i_ = CustList.FIRST_INDEX;
            for (Argument a: firstArgs_) {
                if (i_ < params_.size()) {
                    Struct str_ = a.getStruct();
                    if (PrimitiveTypeUtil.primitiveTypeNullObject(params_.get(i_), str_, _conf)) {
                        _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                        Argument a_ = new Argument();
                        return ArgumentCall.newArgument(a_);
                    }
                    if (!str_.isNull()) {
                        Mapping mapping_ = new Mapping();
                        mapping_.setArg(a.getObjectClassName(_conf));
                        mapping_.setParam(params_.get(i_));
                        if (!Templates.isCorrect(mapping_, _conf)) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                            Argument a_ = new Argument();
                            return ArgumentCall.newArgument(a_);
                        }
                    }
                    if (str_ instanceof NumberStruct || str_ instanceof CharStruct) {
                        ClassArgumentMatching clArg_ = new ClassArgumentMatching(params_.get(i_));
                        a.setStruct(PrimitiveTypeUtil.convertObject(clArg_, str_, _conf));
                    }
                }
                i_++;
            }
            StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
            called_.add(calledCtor_);
            if (StringList.quickEq(trimMeth_,prefixFunction(CURRENT))) {
                InvokingConstructor inv_ = new InvokingConstructor(calledCtorTemp_, EMPTY_STRING, -1, constId, arg_, firstArgs_, InstancingStep.USING_THIS, called_);
                return ArgumentCall.newCall(inv_);
            }
            if (StringList.quickEq(trimMeth_,prefixFunction(SUPER_ACCESS))) {
                _conf.getLastPage().clearCurrentEls();
                InvokingConstructor inv_ = new InvokingConstructor(calledCtorTemp_, EMPTY_STRING, -1, constId, arg_, firstArgs_, InstancingStep.USING_SUPER, called_);
                return ArgumentCall.newCall(inv_);
            }
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(INSTANCEOF))) {
            String str_ = (String) _arguments.first().getObject();
            str_ = StringList.removeAllSpaces(str_);
            Argument sec_ = _arguments.last();
            if (sec_.isNull()) {
                Argument arg_ = new Argument();
                arg_.setObject(false);
                return ArgumentCall.newArgument(arg_);
            }
            String className_ = stds_.getStructClassName(sec_.getStruct(), _conf);
            if (!correctTemplate) {
                className_ = StringList.getAllTypes(className_).first();
                boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(str_, className_, _conf);
                Argument arg_ = new Argument();
                arg_.setObject(res_);
                return ArgumentCall.newArgument(arg_);
            }
            Mapping mapping_ = new Mapping();
            mapping_.setArg(className_);
            PageEl page_ = _conf.getLastPage();
            str_ = page_.formatVarType(str_, _conf);
            mapping_.setParam(str_);
            boolean res_ = Templates.isCorrect(mapping_, _conf);
            Argument arg_ = new Argument();
            arg_.setObject(res_);
            return ArgumentCall.newArgument(arg_);
        }
        CustList<Argument> firstArgs_;
        Argument arg_ = _previous;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        if (!staticMethod) {
            if (arg_.isNull()) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                Argument a_ = new Argument();
                return ArgumentCall.newArgument(a_);
            }
            if (staticChoiceMethod) {
                classNameFound_ = classMethodId.getClassName();
                String base_ = StringList.getAllTypes(classNameFound_).first();
                String argClassName_ = arg_.getObjectClassName(_conf);
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                lastType_ = Templates.format(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                methodId_ = realId;
            } else {
                classNameFound_ = classMethodId.getClassName();
                classNameFound_ = StringList.getAllTypes(classNameFound_).first();
                String argClassName_ = arg_.getObjectClassName(_conf);
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
                lastType_ = Templates.format(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                argClassName_ = Templates.getGenericString(argClassName_, _conf);
                String base_ = StringList.getAllTypes(argClassName_).first();
                MethodId id_ = classMethodId.getConstraints();
                if (_conf.getMethodBodiesById(classNameFound_, id_).first().isFinalMethod()) {
                    classNameFound_ = classMethodId.getClassName();
                    methodId_ = realId;
                } else {
                    GeneType info_ = _conf.getClassBody(classNameFound_);
                    StringMap<ClassMethodId> overriding_ = TypeUtil.getConcreteMethodsToCall(info_,id_, _conf);
                    if (overriding_.contains(base_)) {
                        ClassMethodId res_ = overriding_.getVal(base_);
                        classNameFound_ = res_.getClassName();
                        methodId_ = res_.getConstraints();
                    } else {
                        classNameFound_ = classMethodId.getClassName();
                        methodId_ = realId;
                    }
                }
            }
        } else {
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            classNameFound_ = classMethodId.getClassName();
            if (classes_.isCustomType(classNameFound_)) {
                InitClassState res_ = classes_.getLocks().getState(_conf, classNameFound_);
                if (res_ == InitClassState.NOT_YET) {
                    InitializatingClass inv_ = new InitializatingClass(classNameFound_);
                    return ArgumentCall.newCall(inv_);
                }
                if (res_ == InitClassState.ERROR) {
                    CausingErrorStruct causing_ = new CausingErrorStruct(classNameFound_);
                    _conf.setException(causing_);
                    return ArgumentCall.newArgument(Argument.createVoid());
                }
            }
        }
        StringList params_ = new StringList();
        if (!staticMethod) {
            String className_ = stds_.getStructClassName(arg_.getStruct(), _conf);
            String classFormat_ = classNameFound_;
            classFormat_ = Templates.getFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_ == null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                Argument a_ = new Argument();
                return ArgumentCall.newArgument(a_);
            }
            int i_ = 0;
            for (String c: methodId_.getParametersTypes()) {
                String c_ = c;
                c_ = Templates.format(classFormat_, c_, _conf);
                if (i_ + 1 == methodId_.getParametersTypes().size() && methodId_.isVararg()) {
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        } else {
            int i_ = 0;
            for (String c: methodId_.getParametersTypes()) {
                String c_ = c;
                if (i_ + 1 == methodId_.getParametersTypes().size() && methodId_.isVararg()) {
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        }
        int i_ = CustList.FIRST_INDEX;
        for (Argument a: firstArgs_) {
            if (i_ < params_.size()) {
                Struct str_ = a.getStruct();
                if (!str_.isNull()) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(a.getObjectClassName(_conf));
                    mapping_.setParam(params_.get(i_));
                    if (!Templates.isCorrect(mapping_, _conf)) {
                        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                        _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                        Argument a_ = new Argument();
                        return ArgumentCall.newArgument(a_);
                    }
                }
            }
            i_++;
        }
        if (!classes_.isCustomType(classNameFound_)) {
            ClassMethodId dyn_ = new ClassMethodId(classNameFound_, methodId_);
            ResultErrorStd res_ = LgNames.invokeMethod(_conf, naturalVararg > -1, dyn_, arg_.getStruct(), Argument.toArgArray(firstArgs_));
            if (res_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
                Argument a_ = new Argument();
                return ArgumentCall.newArgument(a_);
            }
            Argument argRes_ = new Argument();
            argRes_.setStruct(res_.getResult());
            return ArgumentCall.newArgument(argRes_);
        }
        InvokingMethod inv_ = new InvokingMethod(arg_, classNameFound_, methodId_, firstArgs_);
        return ArgumentCall.newCall(inv_);
    }

    @Override
    public ConstructorId getConstId() {
        return constId;
    }

    @Override
    public boolean isSuperConstructorCall() {
        return superConstructorCall;
    }

    @Override
    public boolean isOtherConstructorClass() {
        return otherConstructorClass;
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    boolean isCallMethodCtor() {
        String trimMeth_ = methodName.trim();
        if (StringList.quickEq(trimMeth_,prefixFunction(VAR_ARG))) {
            return false;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(FIRST_OPT))) {
            return false;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(INSTANCEOF))) {
            return false;
        }
        return true;
    }
    public boolean isConstCall() {
        String trimMeth_ = methodName.trim();
        if (StringList.quickEq(trimMeth_,prefixFunction(VAR_ARG))) {
            return true;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(FIRST_OPT))) {
            return true;
        }
        return false;
    }
}
