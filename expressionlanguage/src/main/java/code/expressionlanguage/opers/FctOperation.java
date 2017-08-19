package code.expressionlanguage.opers;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustEnum;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.AbstractMethodException;
import code.expressionlanguage.exceptions.BadFormatPathException;
import code.expressionlanguage.exceptions.BadNumberArgumentException;
import code.expressionlanguage.exceptions.CustomFoundConstructorException;
import code.expressionlanguage.exceptions.CustomFoundMethodException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.NotBooleanException;
import code.expressionlanguage.exceptions.NotEqualableException;
import code.expressionlanguage.exceptions.NotStringException;
import code.expressionlanguage.exceptions.NullGlobalObjectException;
import code.expressionlanguage.exceptions.SettingMemberException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.ProcessXmlMethod;
import code.expressionlanguage.methods.exceptions.UndefinedConstructorException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FctConstraints;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.Struct;
import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class FctOperation extends InvokingOperation {

    private static final int BOOLEAN_ARGS = 3;

    private String methodName;

    private Method method;

    private FctConstraints constId;

    private ClassMethodId classMethodId;
    private FctConstraints methodId;
    private MethodMetaInfo methodMetaInfo;

    private boolean ternary;

    private boolean superConstructorCall;

    private boolean otherConstructorClass;

    private boolean staticChoiceMethod;
    private boolean superAccessMethod;

    public FctOperation(String _el, int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void analyzeLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        if (getParent() == null) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new SettingMemberException(_conf.joinPages());
        }
        analyzeCommon(_nodes, _conf, _op);
    }
    @Override
    public void analyzeRight(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+CURRENT)) {
            //validate calling of constructors of the current class
            String clCurName_ = _conf.getLastPage().getGlobalClass();
            otherConstructorClass = true;
            CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
            constId = getDeclaredCustConstructor(_conf, new ClassArgumentMatching(clCurName_), ClassArgumentMatching.toArgArray(firstArgs_));
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
            constId = getDeclaredCustConstructor(_conf, new ClassArgumentMatching(superClass_), ClassArgumentMatching.toArgArray(firstArgs_));
            if (constId != null) {
                String glClass_ = _conf.getLastPage().getGlobalClass();
                if (!classes_.canAccessConstructor(glClass_, superClass_, constId)) {
                    ConstructorBlock ctr_ = classes_.getConstructorBody(clCurName_, constId);
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
                setResultClass(new ClassArgumentMatching(Class.class.getName()));
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
                MethodOperation parent_ = getParent();
                if (parent_ instanceof InvokingOperation) {
                    //TODO multiple cast
                    ((InvokingOperation)parent_).isCallMethodCtor();
                }
                checkExist(_conf, str_, true, true, chidren_.first().getIndexInEl()+1);
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
        setNeedPrevious(true);
        setResetablePreviousArg(true);
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
        ClassArgumentMatching clCur_ = getPreviousResultClass();
        if (clCur_ == null) {
            throw new NullGlobalObjectException(_conf.joinPages());
        }
        if (classes_ != null) {
            String clCurName_ = clCur_.getName();
            String glClass_ = _conf.getLastPage().getGlobalClass();
            ClassMetaInfo custClass_ = null;
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
                        methodId = new FctConstraints(METH_NAME, new EqList<StringList>());
                        methodMetaInfo = new MethodMetaInfo(clCurName_, MethodModifier.NORMAL, new ClassName(String.class.getName(), false));
                        setResultClass(new ClassArgumentMatching(methodMetaInfo.getReturnType().getName()));
                        return;
                    }
                    if (StringList.quickEq(trimMeth_, METH_ORDINAL) && firstArgs_.isEmpty()) {
                        if (isStaticAccess()) {
                            throw new StaticAccessException(_conf.joinPages());
                        }
                        methodId = new FctConstraints(METH_ORDINAL, new EqList<StringList>());
                        methodMetaInfo = new MethodMetaInfo(clCurName_, MethodModifier.NORMAL, new ClassName(PrimitiveTypeUtil.PRIM_INT, false));
                        setResultClass(new ClassArgumentMatching(methodMetaInfo.getReturnType().getName()));
                        return;
                    }
                    if (StringList.quickEq(trimMeth_, METH_VALUES) && firstArgs_.isEmpty()) {
                        methodId = new FctConstraints(METH_VALUES, new EqList<StringList>());
                        ClassName ret_ = new ClassName(PrimitiveTypeUtil.getPrettyArrayType(clCurName_), false);
                        methodMetaInfo = new MethodMetaInfo(clCurName_, MethodModifier.NORMAL, ret_);
                        setResultClass(new ClassArgumentMatching(methodMetaInfo.getReturnType().getName()));
                        return;
                    }
                }
                boolean superClassAccess_ = true;
                if (trimMeth_.contains(STATIC_CALL)) {
                    StringList classMethod_ = StringList.splitStrings(trimMeth_, STATIC_CALL);
                    if (classMethod_.size() != 2) {
                        throw new BadFormatPathException(trimMeth_+RETURN_LINE+_conf.joinPages());
                    }
                    String className_ = classMethod_.first();
                    className_ = StringList.removeAllSpaces(className_);
                    className_ = className_.replace(EXTERN_CLASS, DOT_VAR);
                    checkExist(_conf, className_, true, true, getIndexInEl()+off_);
                    clCurName_ = className_;
                    trimMeth_ = classMethod_.last();
                    staticChoiceMethod = true;
                    superClassAccess_ = false;
                } else if (trimMeth_.startsWith(EXTERN_CLASS+SUPER_ACCESS+EXTERN_CLASS)) {
                    String superClass_ = custClass_.getSuperClass();
                    if (StringList.quickEq(superClass_, Object.class.getName())) {
                        throw new NoSuchDeclaredMethodException(trimMeth_+RETURN_LINE+_conf.joinPages());
                    }
                    trimMeth_ = trimMeth_.substring((EXTERN_CLASS+SUPER_ACCESS+EXTERN_CLASS).length());
                    clCurName_ = superClass_;
                    staticChoiceMethod = true;
                    superAccessMethod = true;
                }
                ClassMethodId clMeth_ = getDeclaredCustMethod(_conf, new ClassArgumentMatching(clCurName_), trimMeth_, superClassAccess_, ClassArgumentMatching.toArgArray(firstArgs_));
                methodId = clMeth_.getConstraints();
                String foundClass_ = clMeth_.getClassName().getName();
                classMethodId = clMeth_;
                if (!classes_.canAccessMethod(glClass_, foundClass_, methodId)) {
                    setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
                    throw new BadAccessException(clMeth_.getMethod().getSignature()+RETURN_LINE+_conf.joinPages());
                }
                custClass_ = classes_.getClassMetaInfo(foundClass_);
                MethodMetaInfo methodMetaInfo_ = custClass_.getMethods().getVal(methodId);
                if (isStaticAccess() && methodMetaInfo_.getModifier() != MethodModifier.STATIC) {
                    throw new StaticAccessException(_conf.joinPages());
                }
                if (staticChoiceMethod && methodMetaInfo_.getModifier() == MethodModifier.ABSTRACT) {
                    setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
                    throw new AbstractMethodException(clMeth_.getMethod().getSignature()+RETURN_LINE+_conf.joinPages());
                }
                methodMetaInfo = methodMetaInfo_;
                setResultClass(new ClassArgumentMatching(methodMetaInfo.getReturnType().getName()));
                return;
            }
        }
        if (firstArgs_.isEmpty()) {
            String lang_ = _conf.getLanguage();
            if (lang_ != null) {
                if (StringList.quickEq(lang_, JAVA)) {
                    if (StringList.quickEq(trimMeth_, JAVA_GET_CLASS)) {
                        if (isStaticAccess()) {
                            throw new StaticAccessException(_conf.joinPages());
                        }
                        method = getDeclaredMethod(_conf, new ClassArgumentMatching(Object.class.getName()), trimMeth_, ClassArgumentMatching.toArgArray(firstArgs_));
                        setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getAliasArrayClass(Class.class)));
                        return;
                    }
                }
                if (StringList.quickEq(lang_, CSHARP)) {
                    if (StringList.quickEq(trimMeth_, CSHARP_GET_TYPE)) {
                        if (isStaticAccess()) {
                            throw new StaticAccessException(_conf.joinPages());
                        }
                        setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getAliasArrayClass(Class.class)));
                        return;
                    }
                }
            }
        }
        Method m_ = getDeclaredMethod(_conf, clCur_, trimMeth_, ClassArgumentMatching.toArgArray(firstArgs_));
        if (!canBeUsed(m_, _conf)) {
            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
            throw new BadAccessException(m_.toString()+RETURN_LINE+_conf.joinPages());
        }
        if (isStaticAccess() && !Modifier.isStatic(m_.getModifiers())) {
            throw new StaticAccessException(_conf.joinPages());
        }
        method = m_;
        setAccess(method, _conf);
        if (m_.getReturnType().isPrimitive()) {
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM+m_.getReturnType().getName()));
        } else {
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getAliasArrayClass(m_.getReturnType())));
        }
    }

    @Override
    public Argument calculateLeft(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }
    
    @Override
    public Argument calculateRight(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
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
    public void calculateLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }
    @Override
    public void calculateRight(CustList<OperationNode> _nodes, ContextEl _conf,
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
        if (constId != null) {
            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CURRENT)) {
                String clCurName_ = _conf.getLastPage().getGlobalClass();
                CustList<Argument> firstArgs_ = listArguments(chidren_, _arguments, false);
                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
                called_.add(clCurName_);
                Argument global_ = _conf.getLastPage().getGlobalArgument();
                throw new CustomFoundConstructorException(clCurName_, called_, constId, global_, firstArgs_, InstancingStep.USING_THIS);
            }
            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+SUPER_ACCESS)) {
                String clCurName_ = _conf.getLastPage().getGlobalClass();
                String superClass_ = _conf.getClasses().getClassMetaInfo(clCurName_).getSuperClass();
                CustList<Argument> firstArgs_ = listArguments(chidren_, _arguments, false);
                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
                called_.add(superClass_);
                _conf.getLastPage().getCurrentEls().clear();
                Argument global_ = _conf.getLastPage().getGlobalArgument();
                throw new CustomFoundConstructorException(superClass_, called_, constId, global_, firstArgs_, InstancingStep.USING_SUPER);
            }
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+INSTANCEOF)) {
            if (chidren_.size() == 2) {
                String str_ = (String) _arguments.first().getObject();
                if (str_ == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NullObjectException(_conf.joinPages());
                }
                str_ = StringList.removeAllSpaces(str_);
                checkExist(_conf, str_, false, true, chidren_.first().getIndexInEl()+1);
                Argument sec_ = _arguments.last();
                if (sec_.isNull()) {
                    Argument arg_ = new Argument();
                    arg_.setObject(false);
                    return arg_;
                }
                String className_ = sec_.getStruct().getClassName();
                boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(str_, className_, classes_);
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
                str_ = PrimitiveTypeUtil.getArrayClass(str_);
                Class<?> cl_;
                try {
                    cl_ = ConstClasses.classForNameNotInit(str_);
                } catch (RuntimeClassNotFoundException _0_) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
                }
                Argument arg_ = new Argument();
                arg_.setObject(cl_);
                return arg_;
            }
            if (chidren_.size() == 2) {
                OperationNode oTwo_ = chidren_.last();
                Argument objArg_ = _arguments.last();
                if (objArg_.isNull()) {
                    Argument arg_ = new Argument();
                    return arg_;
                }
                Argument classArg_ = _arguments.first();
                String argClassName_ = objArg_.getObjectClassName();
                ClassArgumentMatching resCl_ = getResultClass();
                String paramName_ = (String) classArg_.getObject();
                String className_ = oTwo_.getResultClass().getName();
                if (className_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    className_ = className_.substring(1);
                }
                if (!resCl_.isPrimitive() || ConstClasses.getPrimitiveClass(className_) == null) {
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(paramName_, argClassName_, classes_)) {
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
        String clCur_ = getPreviousResultClass().getName();
        if (methodId != null) {
            firstArgs_ = listArguments(chidren_, _arguments, false);
            String classNameFound_;
            if (!methodMetaInfo.isStatic()) {
                if (arg_.isNull()) {
                    throw new NullObjectException(_conf.joinPages());
                }
                ClassMetaInfo custClass_ = null;
                String className_ = arg_.getStruct().getClassName();
                custClass_ = classes_.getClassMetaInfo(className_);
                if (custClass_.getCategory() == ClassCategory.ENUM) {
                    if (methodId.eq(new FctConstraints(METH_NAME, new EqList<StringList>()))) {
                        CustEnum cen_ = (CustEnum) arg_.getStruct().getInstance();
                        String name_ = cen_.name();
                        Argument argres_ = new Argument();
                        argres_.setObject(name_);
                        return argres_;
                    }
                    if (methodId.eq(new FctConstraints(METH_ORDINAL, new EqList<StringList>()))) {
                        CustEnum cen_ = (CustEnum) arg_.getStruct().getInstance();
                        int name_ = cen_.ordinal();
                        Argument argres_ = new Argument();
                        argres_.setObject(name_);
                        return argres_;
                    }
                }
                if (staticChoiceMethod) {
                    classNameFound_ = classMethodId.getClassName().getName();
                    if (!superAccessMethod) {
                        String argClassName_ = arg_.getObjectClassName();
                        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, argClassName_, classes_)) {
                            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                            throw new DynamicCastClassException(argClassName_+RETURN_LINE+classNameFound_+RETURN_LINE+_conf.joinPages());
                        }
                    }
                } else {
                    classNameFound_ = getDeclaredCustMethod(_conf, arg_.getObjectClassName(), classMethodId);
                }
            } else {
                ClassMetaInfo custClass_ = null;
                String className_ = clCur_;
                custClass_ = classes_.getClassMetaInfo(className_);
                if (custClass_.getCategory() == ClassCategory.ENUM) {
                    if (methodId.eq(new FctConstraints(METH_VALUES, new EqList<StringList>()))) {
                        CustList<Struct> enums_ = new CustList<Struct>();
                        for (EntryCust<String, FieldMetaInfo> e: custClass_.getFields().entryList()) {
                            if (e.getValue().isEnumElement()) {
                                enums_.add(classes_.getStaticField(new ClassField(className_, e.getKey())));
                            }
                        }
                        Object o_ = Array.newInstance(Struct.class, enums_.size());
                        int i_ = CustList.FIRST_INDEX;
                        for (Struct o: enums_) {
                            Array.set(o_, i_, o);
                            i_++;
                        }
                        String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(className_);
                        Argument argres_ = new Argument();
                        argres_.setStruct(new Struct(o_,clArr_));
                        return argres_;
                    }
                }
                classNameFound_ = classMethodId.getClassName().getName();
            }
            if (_processInit) {
                return ProcessXmlMethod.calculateArgument(arg_, classNameFound_, methodId, firstArgs_, _conf);
            }
            throw new CustomFoundMethodException(arg_, classNameFound_, methodId, firstArgs_);
        }
        firstArgs_ = listArguments(chidren_, _arguments, true);
        Object obj_ = arg_.getObject();
        if (!Modifier.isStatic(method.getModifiers()) && obj_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        if (method.getParameterTypes().length == 0) {
            String lang_ = _conf.getLanguage();
            if (lang_ != null) {
                if (StringList.quickEq(lang_, JAVA)) {
                    if (StringList.quickEq(method.getName(), JAVA_GET_CLASS)) {
                        Argument argres_ = new Argument();
                        argres_.setObject(ConstClasses.classForNameNotInit(PrimitiveTypeUtil.getArrayClass(arg_.getObjectClassName())));
                        return argres_;
                    }
                }
                if (StringList.quickEq(lang_, CSHARP)) {
                    if (StringList.quickEq(method.getName(), CSHARP_GET_TYPE)) {
                        Argument argres_ = new Argument();
                        argres_.setObject(ConstClasses.classForNameNotInit(arg_.getObjectClassName()));
                        return argres_;
                    }
                }
            }
        }
        Struct ret_ = invokeMethod(_conf, 0, clCur_, method, obj_, getObjects(Argument.toArgArray(firstArgs_)));
        Argument argres_ = new Argument();
        argres_.setStruct(ret_);
        return argres_;
    }
    public boolean isTernary() {
        return ternary;
    }

    @Override
    public FctConstraints getConstId() {
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
