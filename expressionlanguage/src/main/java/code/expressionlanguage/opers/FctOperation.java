package code.expressionlanguage.opers;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustEnum;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.InvokingConstructor;
import code.expressionlanguage.InvokingMethod;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.AbstractMethodException;
import code.expressionlanguage.exceptions.BadFormatPathException;
import code.expressionlanguage.exceptions.BadNumberArgumentException;
import code.expressionlanguage.exceptions.CustomFoundConstructorException;
import code.expressionlanguage.exceptions.CustomFoundMethodException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.NotBooleanException;
import code.expressionlanguage.exceptions.NotEqualableException;
import code.expressionlanguage.exceptions.NotInitializedClassException;
import code.expressionlanguage.exceptions.NotStringException;
import code.expressionlanguage.exceptions.NullGlobalObjectException;
import code.expressionlanguage.exceptions.PrimitiveTypeException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.exceptions.VarargException;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.PredefinedClasses;
import code.expressionlanguage.methods.ProcessXmlMethod;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.exceptions.UndefinedConstructorException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.CustStruct;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.types.NativeTypeUtil;
import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class FctOperation extends InvokingOperation {

    private static final int BOOLEAN_ARGS = 3;

    private String methodName;

    private Method method;

    private ConstructorId constId;

    private ClassMethodId classMethodId;
    private MethodId realId;

    private boolean staticMethod;

    private boolean ternary;

    private boolean superConstructorCall;

    private boolean otherConstructorClass;

    private boolean staticChoiceMethod;
    private boolean staticChoiceMethodTemplate;

    private boolean superAccessMethod;

    private boolean foundBound;
    private boolean correctTemplate = true;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

    public FctOperation(int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            String _fieldName, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        int varargOnly_ = lookOnlyForVarArg();
        LgNames stds_ = _conf.getStandards();
        String stringType_ = stds_.getAliasString();
        String booleanType_ = stds_.getAliasBoolean();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+VAR_ARG)) {
            setVararg(true);
            if (!(getParent() instanceof InvokingOperation)) {
                throw new VarargException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            InvokingOperation parent_ = (InvokingOperation) getParent();
            if (!parent_.isCallMethodCtor()) {
                throw new VarargException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            if (!isFirstChild()) {
                throw new VarargException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            if (chidren_.size() != CustList.ONE_ELEMENT) {
                throw new VarargException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            if (!chidren_.first().getResultClass().matchClass(stringType_)) {
                setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                throw new NotStringException(chidren_.first().getResultClass()+RETURN_LINE+_conf.joinPages());
            }
            if (chidren_.first().getArgument() == null) {
                setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                throw new NullObjectException(_conf.joinPages());
            }
            String str_ = (String) chidren_.first().getArgument().getObject();
            if (str_ == null) {
                setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                throw new NullObjectException(_conf.joinPages());
            }
            str_ = StringList.removeAllSpaces(str_);
            checkCorrect(_conf, str_, true, chidren_.first().getIndexInEl()+1);
            setResultClass(new ClassArgumentMatching(str_));
            setSimpleArgument(new Argument());
            return;
        }
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+FIRST_OPT)) {
            setFirstOptArg(true);
            if (!(getParent() instanceof InvokingOperation)) {
                throw new VarargException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            InvokingOperation parent_ = (InvokingOperation) getParent();
            if (!parent_.isCallMethodCtor()) {
                throw new VarargException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            if (isFirstChild()) {
                throw new VarargException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            if (chidren_.size() != CustList.ONE_ELEMENT) {
                throw new VarargException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            setResultClass(chidren_.first().getResultClass());
            return;
        }
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+CURRENT)) {
            //validate calling of constructors of the current class
            String clCurName_ = _conf.getLastPage().getGlobalClass();
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
            throw new UndefinedConstructorException(constId_.getSignature()+RETURN_LINE+_conf.joinPages());
        }
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+SUPER_ACCESS)) {
            //validate calling of super constructors
            String clCurName_ = _conf.getLastPage().getGlobalClass();
            String base_ = StringList.getAllTypes(clCurName_).first();
            superConstructorCall = true;
            CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
            UniqueRootedBlock unique_ =(UniqueRootedBlock) _conf.getClasses().getClassBody(base_);
            String superClass_ = Templates.format(clCurName_, unique_.getGenericSuperClass(), _conf);
            ConstrustorIdVarArg ctorRes_;
            ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, new ClassArgumentMatching(superClass_), ClassArgumentMatching.toArgArray(firstArgs_));
            if (ctorRes_ != null) {
                constId = ctorRes_.getRealId();
                CustList<ConstructorBlock> ctors_ = classes_.getConstructorBodiesById(superClass_, constId);
                if (!ctors_.isEmpty() && !classes_.canAccess(clCurName_, ctors_.first(), _conf)) {
                    ConstructorBlock ctr_ = ctors_.first();
                    throw new BadAccessException(ctr_.getId().getSignature()+RETURN_LINE+_conf.joinPages());
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
            throw new UndefinedConstructorException(constId_.getSignature()+RETURN_LINE+_conf.joinPages());
        }
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+INSTANCEOF)) {
            if (chidren_.size() == 2) {
                if (!chidren_.first().getResultClass().matchClass(stringType_)) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NotStringException(chidren_.first().getResultClass()+RETURN_LINE+_conf.joinPages());
                }
                if (chidren_.first().getArgument() == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NullObjectException(_conf.joinPages());
                }
                String str_ = (String) chidren_.first().getArgument() .getObject();
                if (str_ == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NullObjectException(_conf.joinPages());
                }
                str_ = StringList.removeAllSpaces(str_);
                if (str_.contains(Templates.TEMPLATE_BEGIN)) {
                    checkCorrect(_conf, str_, true, chidren_.first().getIndexInEl()+1);
                } else {
                    checkExistBase(_conf, !isStaticBlock(), str_, true, chidren_.first().getIndexInEl()+1);
                    if (!str_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                        correctTemplate = Templates.correctNbParameters(str_, _conf);
                    }
                }
                setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
                return;
            }
            throw new BadNumberArgumentException(EXTERN_CLASS+INSTANCEOF+RETURN_LINE+chidren_.size()+RETURN_LINE+_conf.joinPages());
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CAST)) {
            if (chidren_.size() == 1) {
                if (!chidren_.first().getResultClass().matchClass(stringType_)) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NotStringException(chidren_.first().getResultClass()+RETURN_LINE+_conf.joinPages());
                }
                setResultClass(new ClassArgumentMatching(ClassMetaInfo.class.getName()));
                return;
            }
            if (chidren_.size() == 2) {
                if (!chidren_.first().getResultClass().matchClass(stringType_)) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NotStringException(chidren_.first().getResultClass()+RETURN_LINE+_conf.joinPages());
                }
                if (chidren_.first().getArgument() == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NullObjectException(_conf.joinPages());
                }
                String str_ = (String) chidren_.first().getArgument().getObject();
                if (str_ == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NullObjectException(_conf.joinPages());
                }
                str_ = StringList.removeAllSpaces(str_);
                checkCorrect(_conf, str_, true, chidren_.first().getIndexInEl()+1);
                setResultClass(new ClassArgumentMatching(str_));
                return;
            }
            throw new BadNumberArgumentException(EXTERN_CLASS+CAST+RETURN_LINE+chidren_.size()+RETURN_LINE+_conf.joinPages());
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+BOOLEAN)) {
            if (chidren_.size() != BOOLEAN_ARGS) {
                throw new BadNumberArgumentException(EXTERN_CLASS+CAST+RETURN_LINE+chidren_.size()+RETURN_LINE+_conf.joinPages());
            }
            OperationNode opOne_ = chidren_.first();
            ClassArgumentMatching clMatch_ = opOne_.getResultClass();
            if (!clMatch_.matchClass(booleanPrimType_)) {
                if (!clMatch_.matchClass(booleanType_)) {
                    setRelativeOffsetPossibleLastPage(opOne_.getIndexInEl()+1, _conf);
                    throw new NotBooleanException(clMatch_+RETURN_LINE+_conf.joinPages());
                }
            }
            OperationNode opTwo_ = chidren_.get(CustList.SECOND_INDEX);
            OperationNode opThree_ = chidren_.get(CustList.SECOND_INDEX);
            ClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
            ClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
            if (!clMatchTwo_.matchClass(clMatchThree_)) {
                setRelativeOffsetPossibleLastPage(opTwo_.getIndexInEl()+1, _conf);
                throw new NotEqualableException(clMatchTwo_+RETURN_LINE+clMatchThree_+RETURN_LINE+_conf.joinPages());
            }
            ternary = true;
            setResultClass(clMatchTwo_);
            return;
        }
        needGlobalArgument();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        ClassArgumentMatching clCur_ = getPreviousResultClass();
        String clCurName_;
        if (trimMeth_.contains(STATIC_CALL)) {
            StringList classMethod_ = StringList.splitStrings(trimMeth_, STATIC_CALL);
            if (classMethod_.size() != 2) {
                throw new BadFormatPathException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            String className_ = classMethod_.first();
            if (!className_.startsWith(CLASS_CHOICE_PREF)) {
                throw new BadFormatPathException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            int lenPref_ = CLASS_CHOICE_PREF.length();
            className_ = className_.substring(lenPref_);
            className_ = StringList.removeAllSpaces(className_);
            className_ = className_.replace(EXTERN_CLASS, DOT_VAR);
            if (className_.contains(Templates.TEMPLATE_BEGIN)) {
                staticChoiceMethodTemplate = true;
                checkCorrect(_conf, className_, true, getIndexInEl()+off_ + lenPref_);
            } else {
                checkExistBase(_conf, false, className_, true, getIndexInEl()+off_ + lenPref_);
            }
            clCurName_ = className_;
            trimMeth_ = classMethod_.last();
            staticChoiceMethod = true;
        } else {
            if (clCur_ == null) {
                throw new NullGlobalObjectException(_conf.joinPages());
            }
            clCurName_ = clCur_.getName();
        }
        if (StringList.quickEq(clCurName_, stds_.getAliasVoid())) {
            throw new VoidArgumentException(_conf.joinPages());
        }
        if (classes_ != null) {
            String objectClassName_ = stds_.getAliasObject();
            if (clCurName_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                String glClass_ = _conf.getLastPage().getGlobalClass();
                String curClassBase_ = StringList.getAllTypes(glClass_).first();
                RootBlock gl_ = classes_.getClassBody(curClassBase_);
                StringMap<StringList> mapping_ = new StringMap<StringList>();
                for (TypeVar t: gl_.getParamTypes()) {
                    mapping_.put(t.getName(), t.getConstraints());
                }
                for (String u:Mapping.getAllUpperBounds(mapping_, clCurName_.substring(1), objectClassName_)) {
                    String baseUpper_ = StringList.getAllTypes(u).first();
                    if (!classes_.isCustomType(baseUpper_)) {
                        analyzeStandardClass(_conf, u, false);
                    } else {
                        analyzeCustomClass(_conf, u, false);
                    }
                    if (foundBound) {
                        return;
                    }
                }
                throw new NoSuchDeclaredMethodException(trimMeth_+RETURN_LINE+_conf.joinPages());
            }
            if (classes_.isCustomType(clCurName_)) {
                analyzeCustomClass(_conf, clCurName_, true);
                return;
            }
            analyzeStandardClass(_conf, clCurName_, true);
            return;
        }
        if (firstArgs_.isEmpty()) {
            if (StringList.quickEq(trimMeth_, GET_CLASS)) {
                setResultClass(new ClassArgumentMatching(NativeTypeUtil.getPrettyType(ClassMetaInfo.class)));
                return;
            }
        }
        if (clCur_.getClassOrNull() == null) {
            throw new RuntimeClassNotFoundException(clCur_.getName()+RETURN_LINE+_conf.joinPages());
        }
        analyzeNativeClass(_conf, clCurName_, true);
    }

    private void analyzeCustomClass(ContextEl _conf, String _subType, boolean _failIfError) {
        Classes classes_ = _conf.getClasses();
        LgNames stds_ = _conf.getStandards();
        String stringType_ = stds_.getAliasString();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        String clCurName_ = _subType;
        int varargOnly_ = lookOnlyForVarArg();
        for (ClassArgumentMatching c:firstArgs_) {
            if (c.matchVoid(_conf)) {
                throw new VoidArgumentException(clCurName_+DOT+trimMeth_+RETURN_LINE+_conf.joinPages());
            }
        }
        String baseClass_ = StringList.getAllTypes(clCurName_).first();
        if (StringList.quickEq(baseClass_, PredefinedClasses.ENUM) || StringList.quickEq(baseClass_, PredefinedClasses.ENUM_PARAM)) {
            if (StringList.quickEq(trimMeth_, METH_NAME) && firstArgs_.isEmpty()) {
                if (isStaticAccess()) {
                    if (_failIfError) {
                        throw new StaticAccessException(_conf.joinPages());
                    }
                    return;
                }
                MethodId methodId_ = new MethodId(false, METH_NAME, new EqList<ClassName>());
                classMethodId = new ClassMethodId(clCurName_, methodId_);
                realId = methodId_;
                setResultClass(new ClassArgumentMatching(stringType_));
                foundBound = true;
                return;
            }
            if (StringList.quickEq(trimMeth_, METH_ORDINAL) && firstArgs_.isEmpty()) {
                if (isStaticAccess()) {
                    if (_failIfError) {
                        throw new StaticAccessException(_conf.joinPages());
                    }
                    return;
                }
                MethodId methodId_ = new MethodId(false, METH_ORDINAL, new EqList<ClassName>());
                classMethodId = new ClassMethodId(clCurName_, methodId_);
                realId = methodId_;
                setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
                foundBound = true;
                return;
            }
        } else if (classes_.getClassBody(baseClass_) instanceof EnumBlock) {
            if (StringList.quickEq(trimMeth_, METH_NAME) && firstArgs_.isEmpty()) {
                if (isStaticAccess()) {
                    if (_failIfError) {
                        throw new StaticAccessException(_conf.joinPages());
                    }
                    return;
                }
                MethodId methodId_ = new MethodId(false, METH_NAME, new EqList<ClassName>());
                classMethodId = new ClassMethodId(clCurName_, methodId_);
                realId = methodId_;
                setResultClass(new ClassArgumentMatching(stringType_));
                foundBound = true;
                return;
            }
            if (StringList.quickEq(trimMeth_, METH_ORDINAL) && firstArgs_.isEmpty()) {
                if (isStaticAccess()) {
                    if (_failIfError) {
                        throw new StaticAccessException(_conf.joinPages());
                    }
                    return;
                }
                MethodId methodId_ = new MethodId(false, METH_ORDINAL, new EqList<ClassName>());
                classMethodId = new ClassMethodId(clCurName_, methodId_);
                realId = methodId_;
                setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
                foundBound = true;
                return;
            }
            if (StringList.quickEq(trimMeth_, METH_VALUES) && firstArgs_.isEmpty()) {
                MethodId methodId_ = new MethodId(true, METH_VALUES, new EqList<ClassName>());
                classMethodId = new ClassMethodId(clCurName_, methodId_);
                realId = methodId_;
                ClassName ret_ = new ClassName(PrimitiveTypeUtil.getPrettyArrayType(clCurName_), false);
                staticMethod = true;
                setResultClass(new ClassArgumentMatching(ret_.getName()));
                foundBound = true;
                return;
            }
            if (StringList.quickEq(trimMeth_, METH_VALUEOF) && firstArgs_.size() == CustList.ONE_ELEMENT) {
                if (!StringList.quickEq(firstArgs_.first().getName(), stringType_)) {
                    throw new NoSuchDeclaredMethodException(trimMeth_+RETURN_LINE+_conf.joinPages());
                }
                MethodId methodId_ = new MethodId(true, METH_VALUEOF, new EqList<ClassName>(new ClassName(stringType_, false)));
                classMethodId = new ClassMethodId(clCurName_, methodId_);
                realId = methodId_;
                ClassName ret_ = new ClassName(clCurName_, false);
                staticMethod = true;
                setResultClass(new ClassArgumentMatching(ret_.getName()));
                foundBound = true;
                return;
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
        } else if (trimMeth_.startsWith(EXTERN_CLASS+SUPER_ACCESS+EXTERN_CLASS)) {
            trimMeth_ = trimMeth_.substring((EXTERN_CLASS+SUPER_ACCESS+EXTERN_CLASS).length());
            staticChoiceMethod_ = true;
            superAccessMethod_ = true;
            accessFromSuper_ = true;
        } else if (trimMeth_.startsWith(EXTERN_CLASS+CURRENT+EXTERN_CLASS)) {
            trimMeth_ = trimMeth_.substring((EXTERN_CLASS+CURRENT+EXTERN_CLASS).length());
            staticChoiceMethod_ = true;
            superAccessMethod_ = true;
        }
        ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(_failIfError, _conf, varargOnly_, isStaticAccess(), new ClassArgumentMatching(clCurName_), trimMeth_, superClassAccess_, accessFromSuper_, ClassArgumentMatching.toArgArray(firstArgs_));
        if (!clMeth_.isFoundMethod()) {
            return;
        }
        if (staticChoiceMethod_) {
            if (clMeth_.isAbstractMethod()) {
                if (_failIfError) {
                    setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
                    throw new AbstractMethodException(clMeth_.getId().getConstraints().getSignature()+RETURN_LINE+_conf.joinPages());
                }
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
            StringList paramtTypes_ = clMeth_.getId().getConstraints().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        superAccessMethod = superAccessMethod_;
        staticChoiceMethod = staticChoiceMethod_;
        staticMethod = clMeth_.isStaticMethod();
        setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
        foundBound = true;
    }
    private void analyzeStandardClass(ContextEl _conf, String _subType, boolean _failIfError) {
        LgNames stds_ = _conf.getStandards();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        String clCurName_ = _subType;
        int varargOnly_ = lookOnlyForVarArg();
        for (ClassArgumentMatching c:firstArgs_) {
            if (c.matchVoid(_conf)) {
                throw new VoidArgumentException(clCurName_+DOT+trimMeth_+RETURN_LINE+_conf.joinPages());
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
        } else if (trimMeth_.startsWith(EXTERN_CLASS+SUPER_ACCESS+EXTERN_CLASS)) {
            trimMeth_ = trimMeth_.substring((EXTERN_CLASS+SUPER_ACCESS+EXTERN_CLASS).length());
            staticChoiceMethod_ = true;
            superAccessMethod_ = true;
            accessFromSuper_ = true;
        } else if (trimMeth_.startsWith(EXTERN_CLASS+CURRENT+EXTERN_CLASS)) {
            trimMeth_ = trimMeth_.substring((EXTERN_CLASS+CURRENT+EXTERN_CLASS).length());
            staticChoiceMethod_ = true;
            superAccessMethod_ = true;
        }
        ClassMethodIdReturn clMeth_ = LgNames.getDeclaredCustMethod(_failIfError, _conf, varargOnly_, isStaticAccess(), new ClassArgumentMatching(clCurName_), trimMeth_, superClassAccess_, accessFromSuper_, ClassArgumentMatching.toArgArray(firstArgs_));
        if (!clMeth_.isFoundMethod()) {
            return;
        }
        if (staticChoiceMethod_) {
            if (clMeth_.isAbstractMethod()) {
                if (_failIfError) {
                    setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
                    throw new AbstractMethodException(clMeth_.getId().getConstraints().getSignature()+RETURN_LINE+_conf.joinPages());
                }
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
            StringList paramtTypes_ = clMeth_.getId().getConstraints().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        superAccessMethod = superAccessMethod_;
        staticChoiceMethod = staticChoiceMethod_;
        staticMethod = clMeth_.isStaticMethod();
        setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
        foundBound = true;
    }
    private void analyzeNativeClass(ContextEl _conf, String _subType, boolean _failIfError) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        if (PrimitiveTypeUtil.isPrimitive(_subType, _conf)) {
            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
            throw new PrimitiveTypeException(_subType+RETURN_LINE+_conf.joinPages());
        }
        ClassArgumentMatching clVar_ = new ClassArgumentMatching(_subType);
        String trimMeth_ = methodName.trim();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        int varargOnly_ = lookOnlyForVarArg();
        Method m_ = getDeclaredMethod(_failIfError, _conf, varargOnly_, isStaticAccess(), clVar_, trimMeth_, ClassArgumentMatching.toArgArray(firstArgs_));
        if (m_ == null) {
            return;
        }
        if (!canBeUsed(m_, _conf)) {
            if (_failIfError) {
                setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
                throw new BadAccessException(m_.toString()+RETURN_LINE+_conf.joinPages());
            }
            return;
        }
        method = m_;
        if (m_.isVarArgs() && varargOnly_ == -1) {
            Class<?>[] params_ = m_.getParameterTypes();
            naturalVararg = params_.length - 1;
            lastType = NativeTypeUtil.getPrettyType(params_[naturalVararg]);
            lastType = PrimitiveTypeUtil.getQuickComponentType(lastType);
        }
        staticMethod = Modifier.isStatic(m_.getModifiers());
        setAccess(m_, _conf);
        int nbParams_ = m_.getTypeParameters().length;
        Type type_ = m_.getGenericReturnType();
        String pre_ = NativeTypeUtil.getFormattedType(m_.getReturnType().getName(), type_.toString(), nbParams_, type_);
        setResultClass(new ClassArgumentMatching(pre_));
        foundBound = true;
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        ArgumentCall argres_ = getArgument(_nodes.getVal(this).getPreviousArgument(), arguments_, _conf);
        if (argres_.isInitClass()) {
            throw new NotInitializedClassException(argres_.getInitClass().getClassName());
        }
        if (argres_.isInvokeConstructor()) {
            InvokingConstructor i_ = argres_.getInvokeConstructor();
            throw new CustomFoundConstructorException(i_.getClassName(), i_.getFieldName(), i_.getCalled(), i_.getId(), i_.getCurrentObject(), i_.getArguments(), i_.getInstanceStep());
        }
        if (argres_.isInvokeMethod()) {
            InvokingMethod i_ = argres_.getInvokeMethod();
            throw new CustomFoundMethodException(i_.getGl(), i_.getClassName(), i_.getId(), i_.getArguments());
        }
        Argument res_ = argres_.getArgument();
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }
    /**@throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException*/

    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument previous_ = getPreviousArgument();
        ArgumentCall argres_ = getArgument(previous_, arguments_, _conf);
        if (argres_.isInitClass()) {
            ProcessXmlMethod.initializeClass(argres_.getInitClass().getClassName(), _conf);
            argres_ = getArgument(previous_, arguments_, _conf);
        }
        Argument res_;
        if (argres_.isInvokeConstructor()) {
            InvokingConstructor i_ = argres_.getInvokeConstructor();
            res_ = ProcessXmlMethod.instanceArgument(i_.getClassName(), i_.getCurrentObject(), i_.getId(), i_.getArguments(), _conf);
        } else if (argres_.isInvokeMethod()) {
            InvokingMethod i_ = argres_.getInvokeMethod();
            res_ = ProcessXmlMethod.calculateArgument(i_.getGl(), i_.getClassName(), i_.getId(), i_.getArguments(), _conf);
        } else {
            res_ = argres_.getArgument();
        }
        setSimpleArgument(res_, _conf);
    }
    ArgumentCall getArgument(Argument _previous, CustList<Argument> _arguments, ContextEl _conf) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+FIRST_OPT)) {
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
            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CURRENT)) {
                Argument arg_ = _conf.getLastPage().getGlobalArgument();
                String clCurName_ = arg_.getObjectClassName(_conf);
                String clCurNameBase_ = StringList.getAllTypes(clCurName_).first();
                CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType, _arguments, _conf);
                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
                called_.add(clCurNameBase_);
                InvokingConstructor inv_ = new InvokingConstructor(clCurName_, EMPTY_STRING, constId, arg_, firstArgs_, InstancingStep.USING_THIS, called_);
                return ArgumentCall.newCall(inv_);
            }
            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+SUPER_ACCESS)) {
                Argument arg_ = _conf.getLastPage().getGlobalArgument();
                String clCurName_ = arg_.getObjectClassName(_conf);
                String gl_ = _conf.getLastPage().getGlobalClass();
                gl_ = StringList.getAllTypes(gl_).first();
                String base_ = StringList.getAllTypes(gl_).first();
                gl_ = Templates.getFullTypeByBases(clCurName_, gl_, _conf);
                UniqueRootedBlock unique_ =(UniqueRootedBlock) _conf.getClasses().getClassBody(base_);
                String superClass_ = Templates.format(gl_, unique_.getGenericSuperClass(), _conf);
                String superClassBase_ = StringList.getAllTypes(superClass_).first();
                CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType, _arguments, _conf);
                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
                called_.add(superClassBase_);
                _conf.getLastPage().clearCurrentEls();
                InvokingConstructor inv_ = new InvokingConstructor(superClass_, EMPTY_STRING, constId, arg_, firstArgs_, InstancingStep.USING_SUPER, called_);
                return ArgumentCall.newCall(inv_);
            }
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+INSTANCEOF)) {
            String str_ = (String) _arguments.first().getObject();
            str_ = StringList.removeAllSpaces(str_);
            Argument sec_ = _arguments.last();
            if (sec_.isNull()) {
                Argument arg_ = new Argument();
                arg_.setObject(false);
                return ArgumentCall.newArgument(arg_);
            }
            String className_ = sec_.getStruct().getClassName(_conf);
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
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CAST)) {
            if (chidren_.size() == 1) {
                String str_ = (String) _arguments.first().getObject();
                if (str_ == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                }
                str_ = StringList.removeAllSpaces(str_);
                if (PrimitiveTypeUtil.isPrimitive(str_, _conf)) {
                    if (!PrimitiveTypeUtil.isExistentPrimitive(str_, _conf)) {
                        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                        throw new InvokeException(new StdStruct(new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages())));
                    }
                    ClassMetaInfo res_ = new ClassMetaInfo(str_, null, null, null, null, null,false,false);
                    Argument arg_ = new Argument();
                    arg_.setObject(res_);
                    return ArgumentCall.newArgument(arg_);
                }
                Class<?> cl_;
                try {
                    cl_ = PrimitiveTypeUtil.getSingleNativeClass(str_);
                } catch (RuntimeClassNotFoundException _0_) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new InvokeException(new StdStruct(new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages())));
                }
                ClassMetaInfo res_ = new ClassMetaInfo(str_, null, null, null, null, null,false,false);
                Argument arg_ = new Argument();
                arg_.setObject(res_);
                return ArgumentCall.newArgument(arg_);
            }
            if (chidren_.size() == 2) {
                Argument objArg_ = _arguments.last();
                Argument classArg_ = _arguments.first();
                String paramName_ = (String) classArg_.getObject();
                if (PrimitiveTypeUtil.primitiveTypeNullObject(paramName_, objArg_.getStruct(), _conf)) {
                    throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
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
                    paramName_ = _conf.getLastPage().format(paramName_, _conf);
                    mapping_.setParam(paramName_);
                    if (!Templates.isCorrect(mapping_, _conf)) {
                        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                        throw new InvokeException(new StdStruct(new CustomError(argClassName_+RETURN_LINE+paramName_+RETURN_LINE+_conf.joinPages()),cast_));
                    }
                    arg_.setStruct(objArg_.getStruct());
                } else {
                    if (PrimitiveTypeUtil.getOrderClass(paramName_, _conf) > 0) {
                        if (PrimitiveTypeUtil.getOrderClass(argClassName_, _conf) == 0) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            throw new InvokeException(new StdStruct(new CustomError(argClassName_+RETURN_LINE+paramName_+RETURN_LINE+_conf.joinPages()),cast_));
                        }
                        arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, objArg_.getStruct(), _conf));
                    } else {
                        String typeNameArg_ = PrimitiveTypeUtil.toPrimitive(new ClassArgumentMatching(argClassName_), true, _conf).getName();
                        if (!StringList.quickEq(typeNameArg_, stds_.getAliasPrimBoolean())) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            throw new InvokeException(new StdStruct(new CustomError(argClassName_+RETURN_LINE+paramName_+RETURN_LINE+_conf.joinPages()),cast_));
                        }
                        arg_.setStruct(objArg_.getStruct());
                    }
                }
                return ArgumentCall.newArgument(arg_);
            }
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+BOOLEAN)) {
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
        if (classMethodId == null) {
            firstArgs_ = listArguments(chidren_, naturalVararg, lastType, _arguments, _conf);
            Object obj_ = arg_.getObject();
            if (!staticMethod && obj_ == null) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            }
            if (firstArgs_.isEmpty()) {
                if (StringList.quickEq(trimMeth_, GET_CLASS)) {
                    Argument argres_ = new Argument();
                    ClassMetaInfo res_ = new ClassMetaInfo(arg_.getObjectClassName(_conf), null, null, null, null, null,false,false);
                    argres_.setObject(res_);
                    return ArgumentCall.newArgument(argres_);
                }
            }
            String clCur_ = getPreviousResultClass().getName();
            Struct ret_ = invokeMethod(_conf, 0, naturalVararg > -1, clCur_, method, obj_, Argument.toArgArray(firstArgs_));
            Argument argres_ = new Argument();
            argres_.setStruct(ret_);
            return ArgumentCall.newArgument(argres_);
        }
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        String baseCl_ = StringList.getAllTypes(classMethodId.getClassName()).first();
        if (!classes_.isCustomType(baseCl_)) {
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            ResultErrorStd res_ = LgNames.invokeMethod(_conf, naturalVararg > -1, classMethodId, arg_.getStruct(), Argument.toArgArray(firstArgs_));
            if (res_.getError() != null) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
            }
            Argument argRes_ = new Argument();
            argRes_.setStruct(res_.getResult());
            return ArgumentCall.newArgument(argRes_);
        }
        if (!staticMethod) {
            if (arg_.isNull()) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            }
            ClassMetaInfo custClass_ = null;
            String className_ = arg_.getStruct().getClassName(_conf);
            custClass_ = classes_.getClassMetaInfo(className_, _conf);
            if (custClass_.getCategory() == ClassCategory.ENUM) {
                if (methodId_.eq(new MethodId(false, METH_NAME, new EqList<ClassName>()))) {
                    CustEnum cen_ = (CustEnum) arg_.getStruct().getInstance();
                    String name_ = cen_.name();
                    Argument argres_ = new Argument();
                    argres_.setObject(name_,stringType_);
                    return ArgumentCall.newArgument(argres_);
                }
                if (methodId_.eq(new MethodId(false, METH_ORDINAL, new EqList<ClassName>()))) {
                    CustEnum cen_ = (CustEnum) arg_.getStruct().getInstance();
                    int name_ = cen_.ordinal();
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
                            throw new InvokeException(new StdStruct(new CustomError(argClassName_+RETURN_LINE+classNameFound_+RETURN_LINE+_conf.joinPages()),cast_));
                        }
                        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                    } else {
                        classNameFound_ = StringList.getAllTypes(classNameFound_).first();
                        String baseArgClassName_ = StringList.getAllTypes(argClassName_).first();
                        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, baseArgClassName_, _conf)) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            throw new InvokeException(new StdStruct(new CustomError(baseArgClassName_+RETURN_LINE+classNameFound_+RETURN_LINE+_conf.joinPages()),cast_));
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
                    int indexType_ = CustList.FIRST_INDEX;
                    for (Argument a: firstArgs_) {
                        Struct str_ = a.getStruct();
                        String type_ = methodId_.getParametersTypes().get(indexType_);
                        if (!str_.isNull()) {
                            Mapping map_ = new Mapping();
                            map_.setArg(str_.getClassName(_conf));
                            map_.setParam(type_);
                            if (!Templates.isCorrect(map_, _conf)) {
                                setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                                throw new InvokeException(new StdStruct(new CustomError(argClassName_+RETURN_LINE+classNameFound_+RETURN_LINE+_conf.joinPages()),cast_));
                            }
                        }
                        indexType_++;
                    }
                } else {
                    firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                }
                methodId_ = realId;
            } else {
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                classNameFound_ = classMethodId.getClassName();
                String argClassName_ = arg_.getObjectClassName(_conf);
                argClassName_ = Templates.getGenericString(argClassName_, _conf);
                String base_ = StringList.getAllTypes(argClassName_).first();
                classNameFound_ = StringList.getAllTypes(classNameFound_).first();
                MethodId id_ = classMethodId.getConstraints();
                if (classes_.getMethodBodiesById(classNameFound_, id_).first().isFinalMethod()) {
                    classNameFound_ = classMethodId.getClassName();
                    methodId_ = realId;
                } else {
                    RootBlock info_ = classes_.getClassBody(classNameFound_);
                    StringMap<ClassMethodId> overriding_ = info_.getConcreteMethodsToCall(id_, _conf);
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
            if (!_conf.getClasses().isInitialized(classNameFound_)) {
                _conf.getClasses().initialize(classNameFound_);
                InitializatingClass inv_ = new InitializatingClass(classNameFound_);
                return ArgumentCall.newCall(inv_);
            }
            custClass_ = classes_.getClassMetaInfo(classNameFound_, _conf);
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
                    argres_.setStruct(new CustStruct(o_,clArr_));
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
        for (String c: methodId_.getParametersTypes()) {
            params_.add(c);
        }
        checkArgumentsForInvoking(_conf, naturalVararg_ > -1, params_, getObjects(Argument.toArgArray(firstArgs_)));
        InvokingMethod inv_ = new InvokingMethod(arg_, classNameFound_, methodId_, firstArgs_);
        return ArgumentCall.newCall(inv_);
    }
    public boolean isTernary() {
        return ternary;
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
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+VAR_ARG)) {
            return false;
        }
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+FIRST_OPT)) {
            return false;
        }
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+INSTANCEOF)) {
            return false;
        }
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+CAST)) {
            return false;
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+BOOLEAN)) {
            return false;
        }
        return true;
    }
}
