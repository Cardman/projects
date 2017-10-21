package code.expressionlanguage.opers;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustEnum;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
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
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.exceptions.VarargException;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.ProcessXmlMethod;
import code.expressionlanguage.methods.exceptions.UndefinedConstructorException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
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
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class FctOperation extends InvokingOperation {

    private static final int BOOLEAN_ARGS = 3;

    private String methodName;

    private Method method;

    private ConstructorId constId;

    private ClassMethodId classMethodId;
    private MethodId methodId;

    private boolean staticMethod;

    private boolean ternary;

    private boolean superConstructorCall;

    private boolean otherConstructorClass;

    private boolean staticChoiceMethod;
    private boolean interfaceChoice;
    private boolean superAccessMethod;

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
        boolean staticBlock_ = isStaticBlock();
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
            checkExist(_conf, str_, true, chidren_.first().getIndexInEl()+1);
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
            CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
            constId = getDeclaredCustConstructor(_conf, staticBlock_, new ClassArgumentMatching(clCurName_), ClassArgumentMatching.toArgArray(firstArgs_));
            if (constId != null) {
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
            superConstructorCall = true;
            CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
            ClassMetaInfo meta_ = _conf.getClasses().getClassMetaInfo(clCurName_);
            String superClass_ = meta_.getSuperClass();
            constId = getDeclaredCustConstructor(_conf, staticBlock_, new ClassArgumentMatching(superClass_), ClassArgumentMatching.toArgArray(firstArgs_));
            if (constId != null) {
                CustList<ConstructorBlock> ctors_ = classes_.getConstructorBodiesByFormattedId(superClass_, constId);
                if (!ctors_.isEmpty() && !classes_.canAccess(clCurName_, ctors_.first())) {
                    ConstructorBlock ctr_ = ctors_.first();
                    throw new BadAccessException(ctr_.getId().getSignature()+RETURN_LINE+_conf.joinPages());
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
                checkExist(_conf, str_, true, chidren_.first().getIndexInEl()+1);
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
                checkExist(_conf, str_, true, chidren_.first().getIndexInEl()+1);
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
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
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
            checkExist(_conf, className_, true, getIndexInEl()+off_ + lenPref_);
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
        if (classes_ != null) {
            String glClass_ = _conf.getLastPage().getGlobalClass();
            ClassMetaInfo custClass_;
            custClass_ = classes_.getClassMetaInfo(clCurName_);
            if (custClass_ != null) {
                for (ClassArgumentMatching c:firstArgs_) {
                    if (c.matchVoid()) {
                        throw new VoidArgumentException(clCurName_+DOT+trimMeth_+RETURN_LINE+_conf.joinPages());
                    }
                }
                if (custClass_.getCategory() == ClassCategory.ENUM) {
                    if (StringList.quickEq(trimMeth_, METH_NAME) && firstArgs_.isEmpty()) {
                        if (isStaticAccess()) {
                            throw new StaticAccessException(_conf.joinPages());
                        }
                        methodId = new MethodId(METH_NAME, new EqList<ClassName>());
                        classMethodId = new ClassMethodId(clCurName_, methodId);
                        setResultClass(new ClassArgumentMatching(String.class.getName()));
                        return;
                    }
                    if (StringList.quickEq(trimMeth_, METH_ORDINAL) && firstArgs_.isEmpty()) {
                        if (isStaticAccess()) {
                            throw new StaticAccessException(_conf.joinPages());
                        }
                        methodId = new MethodId(METH_ORDINAL, new EqList<ClassName>());
                        classMethodId = new ClassMethodId(clCurName_, methodId);
                        setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_INT));
                        return;
                    }
                    if (StringList.quickEq(trimMeth_, METH_VALUES) && firstArgs_.isEmpty()) {
                        methodId = new MethodId(METH_VALUES, new EqList<ClassName>());
                        classMethodId = new ClassMethodId(clCurName_, methodId);
                        ClassName ret_ = new ClassName(PrimitiveTypeUtil.getPrettyArrayType(clCurName_), false);
                        staticMethod = true;
                        setResultClass(new ClassArgumentMatching(ret_.getName()));
                        return;
                    }
                    if (StringList.quickEq(trimMeth_, METH_VALUEOF) && firstArgs_.size() == CustList.ONE_ELEMENT) {
                        if (!StringList.quickEq(firstArgs_.first().getName(), String.class.getName())) {
                            throw new NoSuchDeclaredMethodException(trimMeth_+RETURN_LINE+_conf.joinPages());
                        }
                        methodId = new MethodId(METH_VALUEOF, new EqList<ClassName>(new ClassName(String.class.getName(), false)));
                        classMethodId = new ClassMethodId(clCurName_, methodId);
                        ClassName ret_ = new ClassName(clCurName_, false);
                        staticMethod = true;
                        setResultClass(new ClassArgumentMatching(ret_.getName()));
                        return;
                    }
                }
                boolean superClassAccess_ = true;
                if (trimMeth_.contains(STATIC_CALL)) {
                    StringList classMethod_ = StringList.splitStrings(trimMeth_, STATIC_CALL);
                    trimMeth_ = classMethod_.last();
                    staticChoiceMethod = true;
                    superClassAccess_ = false;
                } else if (trimMeth_.startsWith(EXTERN_CLASS+SUPER_ACCESS+EXTERN_CLASS)) {
                    String superClass_ = custClass_.getSuperClass();
                    if (StringList.quickEq(superClass_, Object.class.getName())) {
                        throw new NoSuchDeclaredMethodException(trimMeth_+RETURN_LINE+_conf.joinPages());
                    }
                    if (custClass_.getCategory() != ClassCategory.CLASS) {
                        throw new NoSuchDeclaredMethodException(trimMeth_+RETURN_LINE+_conf.joinPages());
                    }
                    trimMeth_ = trimMeth_.substring((EXTERN_CLASS+SUPER_ACCESS+EXTERN_CLASS).length());
                    clCurName_ = superClass_;
                    staticChoiceMethod = true;
                    superAccessMethod = true;
                }
                if (superClassAccess_) {
                    custClass_ = classes_.getClassMetaInfo(clCurName_);
                    interfaceChoice = custClass_.getCategory() == ClassCategory.INTERFACE;
                }
                ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(_conf, staticBlock_, isStaticAccess(), new ClassArgumentMatching(clCurName_), trimMeth_, superClassAccess_, ClassArgumentMatching.toArgArray(firstArgs_));
                methodId = clMeth_.getId().getConstraints();
                String foundClass_ = clMeth_.getId().getClassName();
                classMethodId = clMeth_.getId();
                CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(foundClass_, methodId);
                MethodBlock m_;
                if (!methods_.isEmpty()) {
                    m_ = methods_.first();
                } else {
                    String baseFoundClass_ = StringList.getAllTypes(foundClass_).first();
                    String className_ = classes_.getClassBody(baseFoundClass_).getDefaultMethodIds().getVal(methodId);
                    m_ = classes_.getMethodBodiesByFormattedId(className_, methodId).first();
                }
                String curClassBase_ = null;
                if (glClass_ != null) {
                    curClassBase_ = StringList.getAllTypes(glClass_).first();
                }
                if (!classes_.canAccess(curClassBase_, m_)) {
                    setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
                    throw new BadAccessException(clMeth_.getId().getConstraints().getSignature()+RETURN_LINE+_conf.joinPages());
                }
                staticMethod = clMeth_.isStaticMethod();
                if (staticChoiceMethod) {
                    if (clMeth_.isAbstractMethod()) {
                        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
                        throw new AbstractMethodException(clMeth_.getId().getConstraints().getSignature()+RETURN_LINE+_conf.joinPages());
                    }
                }
                setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
                return;
            }
        }
        if (firstArgs_.isEmpty()) {
            if (StringList.quickEq(trimMeth_, GET_CLASS)) {
                method = getDeclaredMethod(_conf, staticBlock_, isStaticAccess(), new ClassArgumentMatching(Object.class.getName()), trimMeth_, ClassArgumentMatching.toArgArray(firstArgs_));
                setResultClass(new ClassArgumentMatching(NativeTypeUtil.getPrettyType(ClassMetaInfo.class)));
                return;
            }
        }
        Method m_ = getDeclaredMethod(_conf, staticBlock_, isStaticAccess(), clCur_, trimMeth_, ClassArgumentMatching.toArgArray(firstArgs_));
        if (!canBeUsed(m_, _conf)) {
            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
            throw new BadAccessException(m_.toString()+RETURN_LINE+_conf.joinPages());
        }
        method = m_;
        setAccess(method, _conf);
        setResultClass(new ClassArgumentMatching(NativeTypeUtil.getPrettyType(m_.getGenericReturnType())));
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
        Argument argres_ = getArgument(false, _nodes.getVal(this).getPreviousArgument(), arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
        return argres_;
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
        Argument argres_ = getArgument(true, getPreviousArgument(), arguments_, _conf);
        setSimpleArgument(argres_, _conf);
    }
    Argument getArgument(boolean _processInit,Argument _previous, CustList<Argument> _arguments, ContextEl _conf) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+FIRST_OPT)) {
            return _arguments.first();
        }
        if (constId != null) {
            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CURRENT)) {
                String clCurName_ = _conf.getLastPage().getGlobalClass();
                String clCurNameBase_ = StringList.getAllTypes(clCurName_).first();
                CustList<Argument> firstArgs_ = listArguments(chidren_, _arguments, _conf, false);
                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
                called_.add(clCurNameBase_);
                Argument global_ = _conf.getLastPage().getGlobalArgument();
                throw new CustomFoundConstructorException(clCurName_, EMPTY_STRING, called_, constId, global_, firstArgs_, InstancingStep.USING_THIS);
            }
            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+SUPER_ACCESS)) {
                String clCurName_ = _conf.getLastPage().getGlobalClass();
                String superClass_ = _conf.getClasses().getClassMetaInfo(clCurName_).getSuperClass();
                String superClassBase_ = StringList.getAllTypes(superClass_).first();
                CustList<Argument> firstArgs_ = listArguments(chidren_, _arguments, _conf, false);
                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
                called_.add(superClassBase_);
                _conf.getLastPage().clearCurrentEls();
                Argument global_ = _conf.getLastPage().getGlobalArgument();
                throw new CustomFoundConstructorException(superClass_, EMPTY_STRING, called_, constId, global_, firstArgs_, InstancingStep.USING_SUPER);
            }
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+INSTANCEOF)) {
            if (chidren_.size() == 2) {
                String str_ = (String) _arguments.first().getObject();
                str_ = StringList.removeAllSpaces(str_);
                Argument sec_ = _arguments.last();
                if (sec_.isNull()) {
                    Argument arg_ = new Argument();
                    arg_.setObject(false);
                    return arg_;
                }
                String className_ = sec_.getStruct().getClassName();
                Mapping mapping_ = new Mapping();
                mapping_.setArg(className_);
                str_ = _conf.getLastPage().format(str_, classes_);
                mapping_.setParam(str_);
                boolean res_ = Templates.isCorrect(mapping_, classes_);
                Argument arg_ = new Argument();
                arg_.setObject(res_);
                return arg_;
            }
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
                    return arg_;
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
                return arg_;
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
                    return arg_;
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
                return arg_;
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
            return arg_;
        }
        CustList<Argument> firstArgs_;
        Argument arg_ = _previous;
        if (methodId != null) {
            firstArgs_ = listArguments(chidren_, _arguments, _conf, false);
            String classNameFound_;
            if (!staticMethod) {
                if (arg_.isNull()) {
                    throw new NullObjectException(_conf.joinPages());
                }
                ClassMetaInfo custClass_ = null;
                String className_ = arg_.getStruct().getClassName();
                custClass_ = classes_.getClassMetaInfo(className_);
                if (custClass_.getCategory() == ClassCategory.ENUM) {
                    if (methodId.eq(new MethodId(METH_NAME, new EqList<ClassName>()))) {
                        CustEnum cen_ = (CustEnum) arg_.getStruct().getInstance();
                        String name_ = cen_.name();
                        Argument argres_ = new Argument();
                        argres_.setObject(name_);
                        return argres_;
                    }
                    if (methodId.eq(new MethodId(METH_ORDINAL, new EqList<ClassName>()))) {
                        CustEnum cen_ = (CustEnum) arg_.getStruct().getInstance();
                        int name_ = cen_.ordinal();
                        Argument argres_ = new Argument();
                        argres_.setObject(name_);
                        return argres_;
                    }
                }
                if (staticChoiceMethod) {
                    classNameFound_ = classMethodId.getClassName();
                    if (!superAccessMethod) {
                        String argClassName_ = arg_.getObjectClassName();
                        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, argClassName_, classes_)) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            throw new DynamicCastClassException(argClassName_+RETURN_LINE+classNameFound_+RETURN_LINE+_conf.joinPages());
                        }
                    }
                } else {
                    classNameFound_ = getDynDeclaredCustMethod(_conf, arg_.getObjectClassName(), interfaceChoice, classMethodId);
                }
            } else {
                ClassMetaInfo custClass_ = null;
                classNameFound_ = classMethodId.getClassName();
                if (!_conf.getClasses().isInitialized(classNameFound_)) {
                    _conf.getClasses().initialize(classNameFound_);
                    if (!_processInit) {
                        throw new NotInitializedClassException(classNameFound_);
                    }
                    ProcessXmlMethod.initializeClass(classNameFound_, _conf);
                }
                custClass_ = classes_.getClassMetaInfo(classNameFound_);
                if (custClass_.getCategory() == ClassCategory.ENUM) {
                    if (methodId.eq(new MethodId(METH_VALUES, new EqList<ClassName>()))) {
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
                        return argres_;
                    }
                    if (methodId.eq(new MethodId(METH_VALUEOF, new EqList<ClassName>(new ClassName(String.class.getName(),false))))) {
                        if (firstArgs_.first().isNull()) {
                            throw new NullObjectException(_conf.joinPages());
                        }
                        for (EntryCust<String, FieldMetaInfo> e: custClass_.getFields().entryList()) {
                            if (StringList.quickEq(e.getKey(), (String) firstArgs_.first().getObject())) {
                                Argument argres_ = new Argument();
                                argres_.setStruct(classes_.getStaticField(new ClassField(classNameFound_, e.getKey())));
                                return argres_;
                            }
                        }
                        throw new InexistingEnumException(firstArgs_.first().getObject()+RETURN_LINE+_conf.joinPages());
                    }
                }
            }
            StringList params_ = new StringList();
            for (String c: methodId.getParametersTypes()) {
                params_.add(c);
            }
            checkArgumentsForInvoking(_conf, params_, getObjects(Argument.toArgArray(firstArgs_)));
            if (_processInit) {
                return ProcessXmlMethod.calculateArgument(arg_, classNameFound_, methodId, firstArgs_, _conf);
            }
            throw new CustomFoundMethodException(arg_, classNameFound_, methodId, firstArgs_);
        }
        firstArgs_ = listArguments(chidren_, _arguments, _conf, true);
        Object obj_ = arg_.getObject();
        if (!Modifier.isStatic(method.getModifiers()) && obj_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        if (method.getParameterTypes().length == 0) {
            if (StringList.quickEq(method.getName(), GET_CLASS)) {
                Argument argres_ = new Argument();
                ClassMetaInfo res_ = new ClassMetaInfo(arg_.getObjectClassName(), null, null, null, null, null,false,false);
                argres_.setObject(res_);
                return argres_;
            }
        }
        String clCur_ = getPreviousResultClass().getName();
        Struct ret_ = invokeMethod(_conf, 0, clCur_, method, obj_, Argument.toArgArray(firstArgs_));
        Argument argres_ = new Argument();
        argres_.setStruct(ret_);
        return argres_;
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
