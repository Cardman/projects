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
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.PredefinedClasses;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.AbstractMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessConstructor;
import code.expressionlanguage.methods.util.BadFormatPathError;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.StaticAccessMethodError;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UndefinedConstructorError;
import code.expressionlanguage.methods.util.UndefinedMethodError;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.methods.util.VarargError;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.EnumerableStruct;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class FctOperation extends InvokingOperation {

    private static final int BOOLEAN_ARGS = 3;

    private String methodName;

    private ConstructorId constId;

    private ClassMethodId classMethodId;
    private MethodId realId;

    private boolean staticMethod;

    private boolean superConstructorCall;

    private boolean otherConstructorClass;

    private boolean staticChoiceMethod;
    private boolean staticChoiceMethodTemplate;

    private boolean superAccessMethod;

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
        String booleanType_ = stds_.getAliasBoolean();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        if (StringList.quickEq(trimMeth_,prefixFunction(VAR_ARG))) {
            setVararg(true);
            if (!(getParent() instanceof InvokingOperation)) {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(trimMeth_);
                _conf.getClasses().getErrorsDet().add(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            InvokingOperation parent_ = (InvokingOperation) getParent();
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
            if (!(getParent() instanceof InvokingOperation)) {
                VarargError varg_ = new VarargError();
                varg_.setFileName(_conf.getCurrentFileName());
                varg_.setRc(_conf.getCurrentLocation());
                varg_.setMethodName(trimMeth_);
                _conf.getClasses().getErrorsDet().add(varg_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                setSimpleArgument(new Argument());
                return;
            }
            InvokingOperation parent_ = (InvokingOperation) getParent();
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
                setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
                return;
            }
            EqList<ClassName> cl_ = new EqList<ClassName>();
            for (ClassArgumentMatching c: firstArgs_) {
                cl_.add(new ClassName(c.getName(), false));
            }
            ConstructorId constId_ = new ConstructorId(clCurName_, cl_);
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
                setResultClass(new ClassArgumentMatching(stds_.getAliasVoid()));
                return;
            }
            EqList<ClassName> cl_ = new EqList<ClassName>();
            for (ClassArgumentMatching c: firstArgs_) {
                cl_.add(new ClassName(c.getName(), false));
            }
            ConstructorId constId_ = new ConstructorId(superClass_, cl_);
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
        if (StringList.quickEq(trimMeth_,prefixFunction(CAST))) {
//            if (chidren_.size() == 1) {
//                if (!chidren_.first().getResultClass().matchClass(stringType_)) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new NotStringException(StringList.concat(chidren_.first().getResultClass().getName(),RETURN_LINE,_conf.joinPages()));
//                }
//                setResultClass(new ClassArgumentMatching(ClassMetaInfo.class.getName()));
//                return;
//            }
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
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
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
                    return;
                }
                str_ = StringList.removeAllSpaces(str_);
                if (!checkCorrect(_conf, str_, true, chidren_.first().getIndexInEl()+1)) {
                    str_ = stds_.getAliasObject();
                }
                setResultClass(new ClassArgumentMatching(str_));
                return;
            }
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badNb_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(BOOLEAN))) {
            if (chidren_.size() != BOOLEAN_ARGS) {
                BadOperandsNumber badNb_ = new BadOperandsNumber();
                badNb_.setOperandsNumber(chidren_.size());
                badNb_.setFileName(_conf.getCurrentFileName());
                badNb_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(badNb_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            OperationNode opOne_ = chidren_.first();
            ClassArgumentMatching clMatch_ = opOne_.getResultClass();
            if (!clMatch_.matchClass(booleanPrimType_)) {
                if (!clMatch_.matchClass(booleanType_)) {
                    setRelativeOffsetPossibleAnalyzable(opOne_.getIndexInEl()+1, _conf);
                    ClassArgumentMatching cl_ = chidren_.first().getResultClass();
                    UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                    un_.setRc(_conf.getCurrentLocation());
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setExpectedResult(booleanType_);
                    un_.setOperands(new StringList(cl_.getName()));
                    _conf.getClasses().getErrorsDet().add(un_);
                }
            }
            OperationNode opTwo_ = chidren_.get(CustList.SECOND_INDEX);
            OperationNode opThree_ = chidren_.get(CustList.SECOND_INDEX);
            ClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
            ClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
            if (!clMatchTwo_.matchClass(clMatchThree_)) {
                setRelativeOffsetPossibleAnalyzable(opTwo_.getIndexInEl()+1, _conf);
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setExpectedResult(clMatchTwo_.getName());
                un_.setOperands(new StringList(clMatchTwo_.getName(),clMatchThree_.getName()));
                un_.setFileName(_conf.getCurrentFileName());
                un_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(un_);
            }
            setResultClass(clMatchTwo_);
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
        if (trimMeth_.contains(STATIC_CALL)) {
            StringList classMethod_ = StringList.splitStrings(trimMeth_, STATIC_CALL);
            if (classMethod_.size() != 2) {
                BadFormatPathError badFormat_ = new BadFormatPathError();
                badFormat_.setPath(trimMeth_);
                badFormat_.setFileName(_conf.getCurrentFileName());
                badFormat_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(badFormat_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String className_ = classMethod_.first();
            if (!className_.startsWith(CLASS_CHOICE_PREF)) {
                BadFormatPathError badFormat_ = new BadFormatPathError();
                badFormat_.setPath(trimMeth_);
                badFormat_.setFileName(_conf.getCurrentFileName());
                badFormat_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(badFormat_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            int lenPref_ = CLASS_CHOICE_PREF.length();
            className_ = className_.substring(lenPref_);
            className_ = StringList.removeAllSpaces(className_);
            className_ = className_.replace(EXTERN_CLASS, DOT_VAR);
            if (className_.contains(Templates.TEMPLATE_BEGIN)) {
                staticChoiceMethodTemplate = true;
                if (!checkCorrect(_conf, className_, true, getIndexInEl()+off_ + lenPref_)) {
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
            } else {
                if (!checkExistBase(_conf, false, className_, true, getIndexInEl()+off_ + lenPref_)) {
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
            }
            clCurName_ = className_;
            trimMeth_ = classMethod_.last();
            staticChoiceMethod = true;
        } else {
            if (clCur_ == null || clCur_.getName() == null) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(static_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            clCurName_ = clCur_.getName();
        }
        if (StringList.quickEq(clCurName_, stds_.getAliasVoid())) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(clCurName_);
            mapping_.setParam(stds_.getAliasObject());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(cast_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String objectClassName_ = stds_.getAliasObject();
        StringList bounds_ = new StringList();
        if (clCurName_.startsWith(Templates.PREFIX_VAR_TYPE)) {
            String glClass_ = _conf.getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(glClass_).first();
            GeneType gl_ = _conf.getClassBody(curClassBase_);
            StringMap<StringList> mapping_ = new StringMap<StringList>();
            for (TypeVar t: gl_.getParamTypes()) {
                mapping_.put(t.getName(), t.getConstraints());
            }
            bounds_.addAllElts(Mapping.getAllUpperBounds(mapping_, clCurName_.substring(1), objectClassName_));
        } else {
            bounds_.add(clCurName_);
        }
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
        String stringType_ = stds_.getAliasString();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        int varargOnly_ = lookOnlyForVarArg();
        int indexChild_ = -1;
        boolean void_ = false;
        for (ClassArgumentMatching c:firstArgs_) {
            indexChild_++;
            if (c.matchVoid(_conf)) {
                void_ = true;
                if (indexChild_ < chidren_.size()) {
                    OperationNode op_ = chidren_.get(indexChild_);
                    op_.setRelativeOffsetPossibleAnalyzable(op_.getIndexInEl()+off_, _conf);
                } else {
                    setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
                }
                Mapping mapping_ = new Mapping();
                mapping_.setArg(stds_.getAliasVoid());
                mapping_.setParam(stds_.getAliasObject());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(cast_);
            }
        }
        if (void_) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        for (String s: _subTypes) {
            String baseClass_ = StringList.getAllTypes(s).first();
            if (StringList.quickEq(baseClass_, PredefinedClasses.ENUM) || StringList.quickEq(baseClass_, PredefinedClasses.ENUM_PARAM)) {
                if (StringList.quickEq(trimMeth_, METH_NAME) && firstArgs_.isEmpty()) {
                    if (isStaticAccess()) {
                        StaticAccessMethodError static_ = new StaticAccessMethodError();
                        static_.setClassName(s);
                        static_.setId(new MethodId(MethodModifier.NORMAL, trimMeth_, new StringList()));
                        static_.setFileName(_conf.getCurrentFileName());
                        static_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().getErrorsDet().add(static_);
                        setResultClass(new ClassArgumentMatching(stringType_));
                        return;
                    }
                    MethodId methodId_ = new MethodId(false, METH_NAME, new EqList<ClassName>());
                    classMethodId = new ClassMethodId(s, methodId_);
                    realId = methodId_;
                    setResultClass(new ClassArgumentMatching(stringType_));
                    return;
                }
                if (StringList.quickEq(trimMeth_, METH_ORDINAL) && firstArgs_.isEmpty()) {
                    if (isStaticAccess()) {
                        StaticAccessMethodError static_ = new StaticAccessMethodError();
                        static_.setClassName(s);
                        static_.setId(new MethodId(MethodModifier.NORMAL, trimMeth_, new StringList()));
                        static_.setFileName(_conf.getCurrentFileName());
                        static_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().getErrorsDet().add(static_);
                        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
                        return;
                    }
                    MethodId methodId_ = new MethodId(false, METH_ORDINAL, new EqList<ClassName>());
                    classMethodId = new ClassMethodId(s, methodId_);
                    realId = methodId_;
                    setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
                    return;
                }
            } else if (_conf.getClassBody(baseClass_) instanceof EnumBlock) {
                if (StringList.quickEq(trimMeth_, METH_NAME) && firstArgs_.isEmpty()) {
                    if (isStaticAccess()) {
                        StaticAccessMethodError static_ = new StaticAccessMethodError();
                        static_.setClassName(s);
                        static_.setId(new MethodId(MethodModifier.NORMAL, trimMeth_, new StringList()));
                        static_.setFileName(_conf.getCurrentFileName());
                        static_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().getErrorsDet().add(static_);
                        setResultClass(new ClassArgumentMatching(stringType_));
                        return;
                    }
                    MethodId methodId_ = new MethodId(false, METH_NAME, new EqList<ClassName>());
                    classMethodId = new ClassMethodId(s, methodId_);
                    realId = methodId_;
                    setResultClass(new ClassArgumentMatching(stringType_));
                    return;
                }
                if (StringList.quickEq(trimMeth_, METH_ORDINAL) && firstArgs_.isEmpty()) {
                    if (isStaticAccess()) {
                        StaticAccessMethodError static_ = new StaticAccessMethodError();
                        static_.setClassName(s);
                        static_.setId(new MethodId(MethodModifier.NORMAL, trimMeth_, new StringList()));
                        static_.setFileName(_conf.getCurrentFileName());
                        static_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().getErrorsDet().add(static_);
                        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
                        return;
                    }
                    MethodId methodId_ = new MethodId(false, METH_ORDINAL, new EqList<ClassName>());
                    classMethodId = new ClassMethodId(s, methodId_);
                    realId = methodId_;
                    setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
                    return;
                }
                if (StringList.quickEq(trimMeth_, METH_VALUES) && firstArgs_.isEmpty()) {
                    MethodId methodId_ = new MethodId(true, METH_VALUES, new EqList<ClassName>());
                    classMethodId = new ClassMethodId(s, methodId_);
                    realId = methodId_;
                    ClassName ret_ = new ClassName(PrimitiveTypeUtil.getPrettyArrayType(s), false);
                    staticMethod = true;
                    setResultClass(new ClassArgumentMatching(ret_.getName()));
                    return;
                }
                if (StringList.quickEq(trimMeth_, METH_VALUEOF) && firstArgs_.size() == CustList.ONE_ELEMENT) {
                    if (!StringList.quickEq(firstArgs_.first().getName(), stringType_)) {
                        UndefinedMethodError und_ = new UndefinedMethodError();
                        und_.setClassName(_subTypes);
                        und_.setId(new MethodId(MethodModifier.STATIC, trimMeth_, new StringList(firstArgs_.first().getName())));
                        und_.setRc(_conf.getCurrentLocation());
                        und_.setFileName(_conf.getCurrentFileName());
                        _conf.getClasses().getErrorsDet().add(und_);
                        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                        return;
                    }
                    MethodId methodId_ = new MethodId(true, METH_VALUEOF, new EqList<ClassName>(new ClassName(stringType_, false)));
                    classMethodId = new ClassMethodId(s, methodId_);
                    realId = methodId_;
                    ClassName ret_ = new ClassName(s, false);
                    staticMethod = true;
                    setResultClass(new ClassArgumentMatching(ret_.getName()));
                    return;
                }
            }
        }
        
        boolean superClassAccess_ = true;
        boolean staticChoiceMethod_ = false;
        boolean superAccessMethod_ = false;
        boolean accessFromSuper_ = false;
        if (trimMeth_.contains(STATIC_CALL)) {
            StringList classMethod_ = StringList.splitStrings(trimMeth_, STATIC_CALL);
            trimMeth_ = classMethod_.last();
            staticChoiceMethod_ = true;
            superClassAccess_ = false;
        } else if (trimMeth_.startsWith(prefixFunction(StringList.concat(SUPER_ACCESS, String.valueOf(EXTERN_CLASS))))) {
            trimMeth_ = trimMeth_.substring(SUPER_ACCESS.length() + 2);
            staticChoiceMethod_ = true;
            superAccessMethod_ = true;
            accessFromSuper_ = true;
        } else if (trimMeth_.startsWith(prefixFunction(StringList.concat(CURRENT, String.valueOf(EXTERN_CLASS))))) {
            trimMeth_ = trimMeth_.substring(CURRENT.length() + 2);
            staticChoiceMethod_ = true;
            superAccessMethod_ = true;
        }
        ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(_conf, varargOnly_, isStaticAccess(), _subTypes, trimMeth_, superClassAccess_, accessFromSuper_, ClassArgumentMatching.toArgArray(firstArgs_));
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
            if (superAccessMethod_) {
                String foundClass_ = clMeth_.getRealClass();
                foundClass_ = StringList.getAllTypes(foundClass_).first();
                MethodId id_ = clMeth_.getRealId();
                classMethodId = new ClassMethodId(foundClass_, id_);
            } else {
                classMethodId = clMeth_.getId();
            }
        } else {
            String foundClass_ = clMeth_.getRealClass();
            foundClass_ = StringList.getAllTypes(foundClass_).first();
            MethodId id_ = clMeth_.getRealId();
            classMethodId = new ClassMethodId(foundClass_, id_);
        }
        realId = clMeth_.getRealId();
        if (clMeth_.isVarArgToCall()) {
            StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        superAccessMethod = superAccessMethod_;
        staticChoiceMethod = staticChoiceMethod_;
        staticMethod = clMeth_.isStaticMethod();
        setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _firstChild, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        ObjectMap<ClassField,AssignmentBefore> fieldsBefore_ = new ObjectMap<ClassField,AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        if (isTernary()) {
            OperationNode firstChild_ = getFirstChild();
            ObjectMap<ClassField,Assignment> fieldsAfterFirst_ = vars_.getFields().getVal(firstChild_);
            CustList<StringMap<Assignment>> variablesAfterFirst_ = vars_.getVariables().getVal(firstChild_);
            if (firstChild_ == _previous) {
                for (EntryCust<ClassField, Assignment> e: fieldsAfterFirst_.entryList()) {
                    BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                    AssignmentBefore a_ = new AssignmentBefore();
                    if (b_.isAssignedAfterWhenTrue()) {
                        a_.setAssignedBefore(true);
                    }
                    if (b_.isUnassignedAfterWhenTrue()) {
                        a_.setUnassignedBefore(true);
                    }
                    fieldsBefore_.put(e.getKey(), a_);
                }
                for (StringMap<Assignment> s: variablesAfterFirst_) {
                    StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                    for (EntryCust<String, Assignment> e: s.entryList()) {
                        BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                        AssignmentBefore a_ = new AssignmentBefore();
                        if (b_.isAssignedAfterWhenTrue()) {
                            a_.setAssignedBefore(true);
                        }
                        if (b_.isUnassignedAfterWhenTrue()) {
                            a_.setUnassignedBefore(true);
                        }
                        sm_.put(e.getKey(), a_);
                    }
                    variablesBefore_.add(sm_);
                }
            } else {
                for (EntryCust<ClassField, Assignment> e: fieldsAfterFirst_.entryList()) {
                    BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                    AssignmentBefore a_ = new AssignmentBefore();
                    if (b_.isAssignedAfterWhenFalse()) {
                        a_.setAssignedBefore(true);
                    }
                    if (b_.isUnassignedAfterWhenFalse()) {
                        a_.setUnassignedBefore(true);
                    }
                    fieldsBefore_.put(e.getKey(), a_);
                }
                for (StringMap<Assignment> s: variablesAfterFirst_) {
                    StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                    for (EntryCust<String, Assignment> e: s.entryList()) {
                        BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                        AssignmentBefore a_ = new AssignmentBefore();
                        if (b_.isAssignedAfterWhenFalse()) {
                            a_.setAssignedBefore(true);
                        }
                        if (b_.isUnassignedAfterWhenFalse()) {
                            a_.setUnassignedBefore(true);
                        }
                        sm_.put(e.getKey(), a_);
                    }
                    variablesBefore_.add(sm_);
                }
            }
            vars_.getFieldsBefore().put(_firstChild, fieldsBefore_);
            vars_.getVariablesBefore().put(_firstChild, variablesBefore_);
            return;
        }
        for (EntryCust<ClassField, Assignment> e: fieldsAfter_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsBefore_.put(e.getKey(), b_.assignBefore());
        }
        vars_.getFieldsBefore().put(_firstChild, fieldsBefore_);
        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_firstChild, variablesBefore_);
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
        if (isTernary()) {
            OperationNode befLast_ = children_.get(children_.size() - 2);
            ObjectMap<ClassField,Assignment> fieldsAfterBefLast_ = vars_.getFields().getVal(befLast_);
            CustList<StringMap<Assignment>> variablesAfterBefLast_ = vars_.getVariables().getVal(befLast_);
            if (PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf)) {
                for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                    BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                    BooleanAssignment p_ = (BooleanAssignment) fieldsAfterBefLast_.getVal(e.getKey());
                    BooleanAssignment r_ = new BooleanAssignment();
                    if (b_.isAssignedAfterWhenTrue() && p_.isAssignedAfterWhenTrue()) {
                        r_.setAssignedAfterWhenTrue(true);
                    }
                    if (b_.isUnassignedAfterWhenFalse() && p_.isAssignedAfterWhenFalse()) {
                        r_.setUnassignedAfterWhenFalse(true);
                    }
                    fieldsAfter_.put(e.getKey(), r_);
                }
                for (StringMap<Assignment> s: variablesAfterLast_) {
                    StringMap<Assignment> sm_ = new StringMap<Assignment>();
                    int index_ = variablesAfter_.size();
                    for (EntryCust<String, Assignment> e: s.entryList()) {
                        BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                        BooleanAssignment p_ = (BooleanAssignment) variablesAfterBefLast_.get(index_).getVal(e.getKey());
                        BooleanAssignment r_ = new BooleanAssignment();
                        if (b_.isAssignedAfterWhenTrue() && p_.isAssignedAfterWhenTrue()) {
                            r_.setAssignedAfterWhenTrue(true);
                        }
                        if (b_.isUnassignedAfterWhenFalse() && p_.isAssignedAfterWhenFalse()) {
                            r_.setUnassignedAfterWhenFalse(true);
                        }
                        sm_.put(e.getKey(), r_);
                    }
                    variablesAfter_.add(sm_);
                }
            } else {
                for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                    Assignment b_ = e.getValue();
                    Assignment p_ = fieldsAfterBefLast_.getVal(e.getKey());
                    SimpleAssignment r_ = new SimpleAssignment();
                    if (b_.isAssignedAfter() && p_.isAssignedAfter()) {
                        r_.setAssignedAfter(true);
                    }
                    fieldsAfter_.put(e.getKey(), r_);
                }
                for (StringMap<Assignment> s: variablesAfterLast_) {
                    StringMap<Assignment> sm_ = new StringMap<Assignment>();
                    int index_ = variablesAfter_.size();
                    for (EntryCust<String, Assignment> e: s.entryList()) {
                        Assignment b_ = e.getValue();
                        Assignment p_ = variablesAfterBefLast_.get(index_).getVal(e.getKey());
                        SimpleAssignment r_ = new SimpleAssignment();
                        if (b_.isAssignedAfter() && p_.isAssignedAfter()) {
                            r_.setAssignedAfter(true);
                        }
                        sm_.put(e.getKey(), r_);
                    }
                    variablesAfter_.add(sm_);
                }
            }
        } else {
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
        String stringType_;
        null_ = stds_.getAliasNullPe();
        cast_ = stds_.getAliasCast();
        stringType_ = stds_.getAliasString();
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
        if (StringList.quickEq(trimMeth_,prefixFunction(CAST))) {
//            if (chidren_.size() == 1) {
//                String str_ = (String) _arguments.first().getObject();
//                if (str_ == null) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
//                }
//                str_ = StringList.removeAllSpaces(str_);
//                if (PrimitiveTypeUtil.isPrimitive(str_, _conf)) {
//                    if (!PrimitiveTypeUtil.isExistentPrimitive(str_, _conf)) {
//                        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                        throw new InvokeException(new StdStruct(new RuntimeClassNotFoundException(StringList.concat(str_,RETURN_LINE,_conf.joinPages()))));
//                    }
//                    ClassMetaInfo res_ = new ClassMetaInfo(str_, null, null, null, null, null,false,false);
//                    Argument arg_ = new Argument();
//                    arg_.setObject(res_);
//                    return ArgumentCall.newArgument(arg_);
//                }
//                Class<?> cl_;
//                try {
//                    cl_ = PrimitiveTypeUtil.getSingleNativeClass(str_);
//                } catch (RuntimeClassNotFoundException _0_) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new InvokeException(new StdStruct(new RuntimeClassNotFoundException(StringList.concat(str_,RETURN_LINE,_conf.joinPages()))));
//                }
//                ClassMetaInfo res_ = new ClassMetaInfo(str_, null, null, null, null, null,false,false);
//                Argument arg_ = new Argument();
//                arg_.setObject(res_);
//                return ArgumentCall.newArgument(arg_);
//            }
            if (chidren_.size() == 2) {
                Argument objArg_ = _arguments.last();
                Argument classArg_ = _arguments.first();
                String paramName_ = (String) classArg_.getObject();
                if (PrimitiveTypeUtil.primitiveTypeNullObject(paramName_, objArg_.getStruct(), _conf)) {
                    _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                    Argument a_ = new Argument();
                    return ArgumentCall.newArgument(a_);
                }
                if (objArg_.isNull()) {
                    Argument arg_ = new Argument();
                    return ArgumentCall.newArgument(arg_);
                }
                String argClassName_ = objArg_.getObjectClassName(_conf);
                ClassArgumentMatching resCl_ = getResultClass();
                Argument arg_ = new Argument();
                if (!PrimitiveTypeUtil.isPrimitive(paramName_, _conf)) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(argClassName_);
                    paramName_ = _conf.getLastPage().formatVarType(paramName_, _conf);
                    mapping_.setParam(paramName_);
                    if (!Templates.isCorrect(mapping_, _conf)) {
                        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                        _conf.setException(new StdStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,paramName_,RETURN_LINE,_conf.joinPages())),cast_));
                        Argument a_ = new Argument();
                        return ArgumentCall.newArgument(a_);
                    }
                    arg_.setStruct(objArg_.getStruct());
                } else {
                    if (PrimitiveTypeUtil.getOrderClass(paramName_, _conf) > 0) {
                        if (PrimitiveTypeUtil.getOrderClass(argClassName_, _conf) == 0) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            _conf.setException(new StdStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,paramName_,RETURN_LINE,_conf.joinPages())),cast_));
                            Argument a_ = new Argument();
                            return ArgumentCall.newArgument(a_);
                        }
                        arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, objArg_.getStruct(), _conf));
                    } else {
                        String typeNameArg_ = PrimitiveTypeUtil.toPrimitive(new ClassArgumentMatching(argClassName_), true, _conf).getName();
                        if (!StringList.quickEq(typeNameArg_, stds_.getAliasPrimBoolean())) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            _conf.setException(new StdStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,paramName_,RETURN_LINE,_conf.joinPages())),cast_));
                            Argument a_ = new Argument();
                            return ArgumentCall.newArgument(a_);
                        }
                        arg_.setStruct(objArg_.getStruct());
                    }
                }
                return ArgumentCall.newArgument(arg_);
            }
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(BOOLEAN))) {
            Boolean obj_ = (Boolean) _arguments.first().getObject();
            Argument arg_;
            if (obj_) {
                arg_ = _arguments.get(CustList.SECOND_INDEX);
            } else {
                arg_ = _arguments.last();
            }
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
            ClassMetaInfo custClass_ = null;
            String className_ = stds_.getStructClassName(arg_.getStruct(), _conf);
            custClass_ = _conf.getClassMetaInfo(className_);
            if (custClass_.getCategory() == ClassCategory.ENUM) {
                if (methodId_.eq(new MethodId(false, METH_NAME, new EqList<ClassName>()))) {
                    EnumerableStruct cen_ = (EnumerableStruct) arg_.getStruct();
                    String name_ = cen_.getName();
                    Argument argres_ = new Argument();
                    argres_.setObject(name_);
                    return ArgumentCall.newArgument(argres_);
                }
                if (methodId_.eq(new MethodId(false, METH_ORDINAL, new EqList<ClassName>()))) {
                    EnumerableStruct cen_ = (EnumerableStruct) arg_.getStruct();
                    int name_ = cen_.getOrdinal();
                    Argument argres_ = new Argument();
                    argres_.setObject(name_);
                    return ArgumentCall.newArgument(argres_);
                }
            }
            if (staticChoiceMethod) {
                classNameFound_ = classMethodId.getClassName();
                if (!superAccessMethod) {
                    String argClassName_ = arg_.getObjectClassName(_conf);
                    if (staticChoiceMethodTemplate) {
                        classNameFound_ = Templates.format(argClassName_, classNameFound_, _conf);
                        Mapping map_ = new Mapping();
                        map_.setArg(argClassName_);
                        map_.setParam(classNameFound_);
                        if (!Templates.isCorrect(map_, _conf)) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            _conf.setException(new StdStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
                            Argument a_ = new Argument();
                            return ArgumentCall.newArgument(a_);
                        }
                        String base_ = StringList.getAllTypes(classNameFound_).first();
                        String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                        lastType_ = Templates.format(fullClassNameFound_, lastType_, _conf);
                        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                    } else {
                        classNameFound_ = StringList.getAllTypes(classNameFound_).first();
                        String baseArgClassName_ = StringList.getAllTypes(argClassName_).first();
                        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, baseArgClassName_, _conf)) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            _conf.setException(new StdStruct(new CustomError(StringList.concat(baseArgClassName_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
                            Argument a_ = new Argument();
                            return ArgumentCall.newArgument(a_);
                        }
                        classNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
                        methodId_ = realId.format(classNameFound_, _conf);
                        if (!methodId_.isVararg()) {
                            lastType_ = EMPTY_STRING;
                            naturalVararg_ = -1;
                        } else {
                            if (methodId_.getParametersTypes().size() != _arguments.size()) {
                                lastType_ = methodId_.getParametersTypes().last();
                                naturalVararg_ = methodId_.getParametersTypes().size() - 1;
                            } else {
                                Mapping map_ = new Mapping();
                                String param_ = methodId_.getParametersTypes().last();
                                param_ = PrimitiveTypeUtil.getPrettyArrayType(param_);
                                String argClass_ = _arguments.last().getObjectClassName(_conf);
                                map_.setArg(argClass_);
                                map_.setParam(param_);
                                if (argClass_ != null && !Templates.isCorrect(map_, _conf)) {
                                    lastType_ = methodId_.getParametersTypes().last();
                                    naturalVararg_ = methodId_.getParametersTypes().size() - 1;
                                }
                            }
                        }
                        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                    }
                } else {
                    String base_ = StringList.getAllTypes(classNameFound_).first();
                    String argClassName_ = arg_.getObjectClassName(_conf);
                    String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                    lastType_ = Templates.format(fullClassNameFound_, lastType_, _conf);
                    firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                }
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
            ClassMetaInfo custClass_ = null;
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
            custClass_ = _conf.getClassMetaInfo(classNameFound_);
            if (custClass_.getCategory() == ClassCategory.ENUM) {
                if (methodId_.eq(new MethodId(true, METH_VALUES, new EqList<ClassName>()))) {
                    CustList<Struct> enums_ = new CustList<Struct>();
                    for (EntryCust<String, FieldMetaInfo> e: custClass_.getFields().entryList()) {
                        if (e.getValue().isEnumElement()) {
                            enums_.add(classes_.getStaticField(new ClassField(classNameFound_, e.getKey())));
                        }
                    }
                    Struct[] o_ = new Struct[enums_.size()];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct o: enums_) {
                        o_[i_] = o;
                        i_++;
                    }
                    String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(classNameFound_);
                    Argument argres_ = new Argument();
                    argres_.setStruct(new ArrayStruct(o_,clArr_));
                    return ArgumentCall.newArgument(argres_);
                }
                if (methodId_.eq(new MethodId(true, METH_VALUEOF, new EqList<ClassName>(new ClassName(stringType_,false))))) {
                    if (firstArgs_.first().isNull()) {
                        Argument argres_ = new Argument();
                        return ArgumentCall.newArgument(argres_);
                    }
                    for (EntryCust<String, FieldMetaInfo> e: custClass_.getFields().entryList()) {
                        if (StringList.quickEq(e.getKey(), (String) firstArgs_.first().getObject())) {
                            Argument argres_ = new Argument();
                            argres_.setStruct(classes_.getStaticField(new ClassField(classNameFound_, e.getKey())));
                            return ArgumentCall.newArgument(argres_);
                        }
                    }
                    Argument argres_ = new Argument();
                    return ArgumentCall.newArgument(argres_);
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
    public boolean isTernary() {
        String trimMeth_ = methodName.trim();
        return StringList.quickEq(trimMeth_,prefixFunction(BOOLEAN));
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
    public boolean isPossibleInitClass() {
        return false;
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
        if (StringList.quickEq(trimMeth_,prefixFunction(CAST))) {
            return false;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(BOOLEAN))) {
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
        if (StringList.quickEq(trimMeth_,prefixFunction(CAST))) {
            return true;
        }
        if (StringList.quickEq(trimMeth_,prefixFunction(BOOLEAN))) {
            return true;
        }
        return false;
    }
}
