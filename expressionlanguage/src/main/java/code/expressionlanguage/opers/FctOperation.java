package code.expressionlanguage.opers;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustEnum;
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
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InexistingEnumException;
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
import code.expressionlanguage.methods.MethodBlock;
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
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.Struct;
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
            if (!chidren_.first().getResultClass().matchClass(String.class)) {
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
                setResultClass(new ClassArgumentMatching(OperationNode.VOID_RETURN));
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
            String superClass_ = Templates.format(clCurName_, unique_.getGenericSuperClass(), classes_);
            ConstrustorIdVarArg ctorRes_;
            ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, new ClassArgumentMatching(superClass_), ClassArgumentMatching.toArgArray(firstArgs_));
            if (ctorRes_ != null) {
                constId = ctorRes_.getRealId();
                CustList<ConstructorBlock> ctors_ = classes_.getConstructorBodiesById(superClass_, constId);
                if (!ctors_.isEmpty() && !classes_.canAccess(clCurName_, ctors_.first())) {
                    ConstructorBlock ctr_ = ctors_.first();
                    throw new BadAccessException(ctr_.getId().getSignature()+RETURN_LINE+_conf.joinPages());
                }
                if (ctorRes_.isVarArgToCall()) {
                    naturalVararg = constId.getParametersTypes().size() - 1;
                    lastType = constId.getParametersTypes().last();
                }
                setResultClass(new ClassArgumentMatching(OperationNode.VOID_RETURN));
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
                if (!chidren_.first().getResultClass().matchClass(String.class)) {
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
                        correctTemplate = Templates.correctNbParameters(str_, classes_);
                    }
                }
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_BOOLEAN));
                return;
            }
            throw new BadNumberArgumentException(EXTERN_CLASS+INSTANCEOF+RETURN_LINE+chidren_.size()+RETURN_LINE+_conf.joinPages());
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CAST)) {
            if (chidren_.size() == 1) {
                if (!chidren_.first().getResultClass().matchClass(String.class)) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NotStringException(chidren_.first().getResultClass()+RETURN_LINE+_conf.joinPages());
                }
                setResultClass(new ClassArgumentMatching(ClassMetaInfo.class.getName()));
                return;
            }
            if (chidren_.size() == 2) {
                if (!chidren_.first().getResultClass().matchClass(String.class)) {
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
            if (!clMatch_.matchClass(PrimitiveTypeUtil.PRIM_BOOLEAN)) {
                if (!clMatch_.matchClass(Boolean.class)) {
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
        if (StringList.quickEq(clCurName_, OperationNode.VOID_RETURN)) {
            throw new VoidArgumentException(_conf.joinPages());
        }
        if (clCurName_.startsWith(Templates.PREFIX_VAR_TYPE)) {
            String glClass_ = _conf.getLastPage().getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(glClass_).first();
            RootBlock gl_ = classes_.getClassBody(curClassBase_);
            StringMap<StringList> mapping_ = new StringMap<StringList>();
            for (TypeVar t: gl_.getParamTypes()) {
                mapping_.put(t.getName(), t.getConstraints());
            }
            for (String u:Mapping.getAllUpperBounds(mapping_, clCurName_.substring(1))) {
                String baseUpper_ = StringList.getAllTypes(u).first();
                if (!classes_.isCustomType(baseUpper_)) {
                    analyzeNativeClass(_conf, u, false);
                } else {
                    analyzeCustomClass(_conf, u, false);
                }
                if (foundBound) {
                    return;
                }
            }
            throw new NoSuchDeclaredMethodException(trimMeth_+RETURN_LINE+_conf.joinPages());
        }
        if (classes_ != null) {
            if (classes_.isCustomType(clCurName_)) {
                analyzeCustomClass(_conf, clCurName_, true);
                return;
            }
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
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        String clCurName_ = _subType;
        int varargOnly_ = lookOnlyForVarArg();
        for (ClassArgumentMatching c:firstArgs_) {
            if (c.matchVoid()) {
                throw new VoidArgumentException(clCurName_+DOT+trimMeth_+RETURN_LINE+_conf.joinPages());
            }
        }
        String baseClass_ = StringList.getAllTypes(clCurName_).first();
        if (StringList.quickEq(baseClass_, PredefinedClasses.ENUM)) {
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
                setResultClass(new ClassArgumentMatching(String.class.getName()));
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
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_INT));
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
                setResultClass(new ClassArgumentMatching(String.class.getName()));
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
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_INT));
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
                if (!StringList.quickEq(firstArgs_.first().getName(), String.class.getName())) {
                    throw new NoSuchDeclaredMethodException(trimMeth_+RETURN_LINE+_conf.joinPages());
                }
                MethodId methodId_ = new MethodId(true, METH_VALUEOF, new EqList<ClassName>(new ClassName(String.class.getName(), false)));
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
        MethodBlock m_ = clMeth_.getMethod();
        if (staticChoiceMethod_) {
            if (m_.isAbstractMethod()) {
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
        if (PrimitiveTypeUtil.isPrimitive(_subType)) {
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
        if (constId != null) {
            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CURRENT)) {
                Argument arg_ = _conf.getLastPage().getGlobalArgument();
                String clCurName_ = arg_.getObjectClassName();
                String clCurNameBase_ = StringList.getAllTypes(clCurName_).first();
                CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType, _arguments, _conf);
                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
                called_.add(clCurNameBase_);
                InvokingConstructor inv_ = new InvokingConstructor(clCurName_, EMPTY_STRING, constId, arg_, firstArgs_, InstancingStep.USING_THIS, called_);
                return ArgumentCall.newCall(inv_);
            }
            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+SUPER_ACCESS)) {
                Argument arg_ = _conf.getLastPage().getGlobalArgument();
                String clCurName_ = arg_.getObjectClassName();
                String gl_ = _conf.getLastPage().getGlobalClass();
                gl_ = StringList.getAllTypes(gl_).first();
                String base_ = StringList.getAllTypes(gl_).first();
                gl_ = Templates.getFullTypeByBases(clCurName_, gl_, classes_);
                UniqueRootedBlock unique_ =(UniqueRootedBlock) _conf.getClasses().getClassBody(base_);
                String superClass_ = Templates.format(gl_, unique_.getGenericSuperClass(), classes_);
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
            String className_ = sec_.getStruct().getClassName();
            if (!correctTemplate) {
                className_ = StringList.getAllTypes(className_).first();
                boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(str_, className_, classes_);
                Argument arg_ = new Argument();
                arg_.setObject(res_);
                return ArgumentCall.newArgument(arg_);
            }
            Mapping mapping_ = new Mapping();
            mapping_.setArg(className_);
            PageEl page_ = _conf.getLastPage();
            str_ = page_.formatVarType(str_, classes_);
            mapping_.setParam(str_);
            boolean res_ = Templates.isCorrect(mapping_, classes_);
            Argument arg_ = new Argument();
            arg_.setObject(res_);
            return ArgumentCall.newArgument(arg_);
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CAST)) {
            if (chidren_.size() == 1) {
                String str_ = (String) _arguments.first().getObject();
                if (str_ == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NullObjectException(_conf.joinPages());
                }
                str_ = StringList.removeAllSpaces(str_);
                if (PrimitiveTypeUtil.isPrimitive(str_)) {
                    if (PrimitiveTypeUtil.getPrimitiveClass(str_) == null) {
                        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                        throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
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
                    throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
                }
                ClassMetaInfo res_ = new ClassMetaInfo(str_, null, null, null, null, null,false,false);
                Argument arg_ = new Argument();
                arg_.setObject(res_);
                return ArgumentCall.newArgument(arg_);
            }
            if (chidren_.size() == 2) {
                OperationNode oTwo_ = chidren_.last();
                Argument objArg_ = _arguments.last();
                Argument classArg_ = _arguments.first();
                String paramName_ = (String) classArg_.getObject();
                if (PrimitiveTypeUtil.primitiveTypeNullObject(paramName_, objArg_.getStruct())) {
                    throw new NullObjectException(_conf.joinPages());
                }
                if (objArg_.isNull()) {
                    Argument arg_ = new Argument();
                    return ArgumentCall.newArgument(arg_);
                }
                String argClassName_ = objArg_.getObjectClassName();
                ClassArgumentMatching resCl_ = getResultClass();
                String className_ = oTwo_.getResultClass().getName();
                if (!resCl_.isPrimitive() || PrimitiveTypeUtil.getPrimitiveClass(className_) == null) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(argClassName_);
                    paramName_ = _conf.getLastPage().format(paramName_, classes_);
                    mapping_.setParam(paramName_);
                    if (!Templates.isCorrect(mapping_, classes_)) {
                        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                        throw new DynamicCastClassException(argClassName_+RETURN_LINE+paramName_+RETURN_LINE+_conf.joinPages());
                    }
                }
                Argument arg_ = new Argument();
                if (objArg_.getStruct().isJavaObject()) {
                    arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, objArg_.getObject()));
                } else {
                    arg_.setStruct(objArg_.getStruct());
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
                throw new NullObjectException(_conf.joinPages());
            }
            if (firstArgs_.isEmpty()) {
                if (StringList.quickEq(trimMeth_, GET_CLASS)) {
                    Argument argres_ = new Argument();
                    ClassMetaInfo res_ = new ClassMetaInfo(arg_.getObjectClassName(), null, null, null, null, null,false,false);
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
        if (!staticMethod) {
            if (arg_.isNull()) {
                throw new NullObjectException(_conf.joinPages());
            }
            ClassMetaInfo custClass_ = null;
            String className_ = arg_.getStruct().getClassName();
            custClass_ = classes_.getClassMetaInfo(className_);
            if (custClass_.getCategory() == ClassCategory.ENUM) {
                if (methodId_.eq(new MethodId(false, METH_NAME, new EqList<ClassName>()))) {
                    CustEnum cen_ = (CustEnum) arg_.getStruct().getInstance();
                    String name_ = cen_.name();
                    Argument argres_ = new Argument();
                    argres_.setObject(name_);
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
                    String argClassName_ = arg_.getObjectClassName();
                    if (staticChoiceMethodTemplate) {
                        classNameFound_ = Templates.format(argClassName_, classNameFound_, classes_);
                        Mapping map_ = new Mapping();
                        map_.setArg(argClassName_);
                        map_.setParam(classNameFound_);
                        if (!Templates.isCorrect(map_, classes_)) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            throw new DynamicCastClassException(argClassName_+RETURN_LINE+classNameFound_+RETURN_LINE+_conf.joinPages());
                        }
                        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                    } else {
                        classNameFound_ = StringList.getAllTypes(classNameFound_).first();
                        String baseArgClassName_ = StringList.getAllTypes(argClassName_).first();
                        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, baseArgClassName_, classes_)) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            throw new DynamicCastClassException(baseArgClassName_+RETURN_LINE+classNameFound_+RETURN_LINE+_conf.joinPages());
                        }
                        classNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, classes_);
                        methodId_ = realId.format(classNameFound_, classes_);
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
                                String argClass_ = _arguments.last().getObjectClassName();
                                map_.setArg(argClass_);
                                map_.setParam(param_);
                                if (argClass_ != null && !Templates.isCorrect(map_, classes_)) {
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
                            map_.setArg(str_.getClassName());
                            map_.setParam(type_);
                            if (!Templates.isCorrect(map_, classes_)) {
                                setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                                throw new DynamicCastClassException(argClassName_+RETURN_LINE+classNameFound_+RETURN_LINE+_conf.joinPages());
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
                String argClassName_ = arg_.getObjectClassName();
                argClassName_ = Templates.getGenericString(argClassName_, classes_);
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
            custClass_ = classes_.getClassMetaInfo(classNameFound_);
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
                    argres_.setStruct(new Struct(o_,clArr_));
                    return ArgumentCall.newArgument(argres_);
                }
                if (methodId_.eq(new MethodId(true, METH_VALUEOF, new EqList<ClassName>(new ClassName(String.class.getName(),false))))) {
                    if (firstArgs_.first().isNull()) {
                        throw new NullObjectException(_conf.joinPages());
                    }
                    for (EntryCust<String, FieldMetaInfo> e: custClass_.getFields().entryList()) {
                        if (StringList.quickEq(e.getKey(), (String) firstArgs_.first().getObject())) {
                            Argument argres_ = new Argument();
                            argres_.setStruct(classes_.getStaticField(new ClassField(classNameFound_, e.getKey())));
                            return ArgumentCall.newArgument(argres_);
                        }
                    }
                    throw new InexistingEnumException(firstArgs_.first().getObject()+RETURN_LINE+_conf.joinPages());
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
