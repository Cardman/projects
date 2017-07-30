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

//    @Override
//    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting) {
//        if (_setting.getStep() == StepCalculation.LEFT && getParent() == null) {
//            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//            throw new SettingMemberException(_conf.joinPages());
//        }
//        Classes classes_ = _conf.getClasses();
//        ClassMetaInfo custClass_ = null;
//        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
//        int off_ = StringList.getFirstPrintableCharIndex(methodName);
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//        String trimMeth_ = methodName.trim();
//        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+CURRENT)) {
//            //validate calling of constructors of the current class
////            String clCurName_ = _conf.getLastPage().getGlobalArgument().getArgClassName();
//            String clCurName_ = _conf.getLastPage().getGlobalClass();
//            otherConstructorClass = true;
//            CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
//            constId = getDeclaredCustConstructor(_conf, new ClassArgumentMatching(clCurName_), ClassArgumentMatching.toArgArray(firstArgs_));
//            if (constId != null) {
//                setResultClass(new ClassArgumentMatching(OperationNode.VOID_RETURN));
//                return;
//            }
//            EqList<ClassName> cl_ = new EqList<ClassName>();
//            for (ClassArgumentMatching c: firstArgs_) {
//                cl_.add(new ClassName(c.getName(), false));
//            }
//            ConstructorId constId_ = new ConstructorId(clCurName_, cl_);
//            throw new UndefinedConstructorException(constId_.getSignature()+RETURN_LINE+_conf.joinPages());
//        }
//        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+SUPER_ACCESS)) {
//            //validate calling of super constructors
////            String clCurName_ = _conf.getLastPage().getGlobalArgument().getArgClassName();
//            String clCurName_ = _conf.getLastPage().getGlobalClass();
//            superConstructorCall = true;
//            CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
//            ClassMetaInfo meta_ = _conf.getClasses().getClassMetaInfo(clCurName_);
//            String superClass_ = meta_.getSuperClass();
//            constId = getDeclaredCustConstructor(_conf, new ClassArgumentMatching(superClass_), ClassArgumentMatching.toArgArray(firstArgs_));
//            if (constId != null) {
//                setResultClass(new ClassArgumentMatching(OperationNode.VOID_RETURN));
//                return;
//            }
//            EqList<ClassName> cl_ = new EqList<ClassName>();
//            for (ClassArgumentMatching c: firstArgs_) {
//                cl_.add(new ClassName(c.getName(), false));
//            }
//            ConstructorId constId_ = new ConstructorId(superClass_, cl_);
//            throw new UndefinedConstructorException(constId_.getSignature()+RETURN_LINE+_conf.joinPages());
//        }
//        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+INSTANCEOF)) {
//            //            System.out.println(getCallingTest());
//            if (chidren_.size() == 2) {
//                if (!chidren_.first().getResultClass().matchClass(String.class)) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new NotStringException(chidren_.first().getResultClass()+RETURN_LINE+_conf.joinPages());
//                }
//                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_BOOLEAN));
//                return;
//            }
//            throw new BadNumberArgumentException(EXTERN_CLASS+INSTANCEOF+RETURN_LINE+chidren_.size()+RETURN_LINE+_conf.joinPages());
//        }
//        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CAST)) {
//            //            System.out.println(getCallingTest());
//            if (chidren_.size() == 1) {
//                if (!chidren_.first().getResultClass().matchClass(String.class)) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new NotStringException(chidren_.first().getResultClass()+RETURN_LINE+_conf.joinPages());
//                }
//                setResultClass(new ClassArgumentMatching(Class.class.getName()));
//                return;
//            }
//            if (chidren_.size() == 2) {
//                if (!chidren_.first().getResultClass().matchClass(String.class)) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new NotStringException(chidren_.first().getResultClass()+RETURN_LINE+_conf.joinPages());
//                }
//                if (chidren_.first().getArgument() == null) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new NullObjectException(_conf.joinPages());
//                }
//                String str_ = (String) chidren_.first().getArgument().getObject();
//                if (str_ == null) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new NullObjectException(_conf.joinPages());
//                }
//                str_ = StringList.removeAllSpaces(str_);
//                if (classes_ != null) {
//                    custClass_ = classes_.getClassMetaInfo(str_);
//                }
//                //                if (custClass_ == null) {
//                    //                    try {
//                        //                        ConstClasses.classForNameNotInit(str_);
//                        //                    } catch (RuntimeClassNotFoundException _0_) {
//                //                        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                ////                        _conf.addToOffsetPossibleLastPage(methodName.length());
//                //                        throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
//                //                    }
//                //                }
//                checkExist(_conf, str_, false, true, chidren_.first().getIndexInEl()+1);
//                setResultClass(new ClassArgumentMatching(str_));
//                return;
//            }
//            throw new BadNumberArgumentException(EXTERN_CLASS+CAST+RETURN_LINE+chidren_.size()+RETURN_LINE+_conf.joinPages());
//        }
//        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+BOOLEAN)) {
//            if (chidren_.size() != BOOLEAN_ARGS) {
//                throw new BadNumberArgumentException(EXTERN_CLASS+CAST+RETURN_LINE+chidren_.size()+RETURN_LINE+_conf.joinPages());
//            }
//            OperationNode opOne_ = chidren_.first();
//            ClassArgumentMatching clMatch_ = opOne_.getResultClass();
//            if (!clMatch_.matchClass(PrimitiveTypeUtil.PRIM_BOOLEAN)) {
//                if (!clMatch_.matchClass(Boolean.class)) {
//                    setRelativeOffsetPossibleLastPage(opOne_.getIndexInEl()+1, _conf);
//                    throw new NotBooleanException(clMatch_+RETURN_LINE+_conf.joinPages());
//                }
//            }
//            OperationNode opTwo_ = chidren_.get(CustList.SECOND_INDEX);
//            OperationNode opThree_ = chidren_.get(CustList.SECOND_INDEX);
//            ClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
//            ClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
//            if (!clMatchTwo_.matchClass(clMatchThree_)) {
//                setRelativeOffsetPossibleLastPage(opTwo_.getIndexInEl()+1, _conf);
//                throw new NotEqualableException(clMatchTwo_+RETURN_LINE+clMatchThree_+RETURN_LINE+_conf.joinPages());
//            }
//            ternary = true;
//            setResultClass(clMatchTwo_);
//            return;
//        }
//        setNeedPrevious(true);
//        setResetablePreviousArg(true);
//        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
//        ClassArgumentMatching clCur_ = getPreviousResultClass();
//        if (clCur_ == null) {
//            throw new NullGlobalObjectException(_conf.joinPages());
//        }
//        //        MethodMetaInfo metaInfo_ = getDeclaredCustMethod(_conf, clCur_, trimMeth_, firstArgs_.toArray(new ClassArgumentMatching[0]));
//        if (classes_ != null) {
//            String clCurName_ = clCur_.getName();
//            custClass_ = classes_.getClassMetaInfo(clCurName_);
//            if (custClass_ != null) {
//                EqList<MethodId> possibleMethods_ = new EqList<MethodId>();
//                for (ClassArgumentMatching c:firstArgs_) {
//                    if (c.matchVoid()) {
//                        throw new VoidArgumentException(clCurName_+DOT+trimMeth_+RETURN_LINE+_conf.joinPages());
//                    }
//                }
//                for (EntryCust<MethodId, MethodMetaInfo> e: custClass_.getMethods().entryList()) {
//                    if (!StringList.quickEq(e.getKey().getName(), trimMeth_)) {
//                        continue;
//                    }
//                    EqList<ClassName> params_ = e.getKey().getClassNames();
//                    ClassMatching[] p_ = new ClassMatching[params_.size()];
//                    int i_ = CustList.FIRST_INDEX;
//                    for (ClassName c: params_) {
//                        p_[i_] = new ClassMatching(c.getName());
//                        i_++;
//                    }
//                    if (!isPossibleMethod(_conf, p_, ClassArgumentMatching.toArgArray(firstArgs_))) {
//                        continue;
//                    }
//                    possibleMethods_.add(e.getKey());
//                }
//                if (possibleMethods_.isEmpty()) {
//                    String trace_ = clCurName_+DOT+trimMeth_+PAR_LEFT;
//                    StringList classesNames_ = new StringList();
//                    for (ClassArgumentMatching c: firstArgs_) {
//                        classesNames_.add(c.getName());
//                    }
//                    trace_ += classesNames_.join(SEP_ARG);
//                    trace_ += PAR_RIGHT;
//                    throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
//                }
//                if (possibleMethods_.size() == CustList.ONE_ELEMENT) {
//                    methodId = possibleMethods_.first();
//                    MethodMetaInfo methodMetaInfo_ = custClass_.getMethods().getVal(methodId);
//                    setResultClass(new ClassArgumentMatching(methodMetaInfo_.getReturnType().getName()));
//                    return;
//                }
//                ArgumentsGroup gr_ = new ArgumentsGroup(_conf.getClasses(), firstArgs_);
////                ParametersGroupComparator<MethodInfo> cmp_ = new ParametersGroupComparator<MethodInfo>(gr_);
//                CustList<MethodInfo> signatures_ = new CustList<MethodInfo>();
//                for (MethodId m: possibleMethods_) {
//                    ParametersGroup p_ = new ParametersGroup();
//                    for (ClassName c: m.getClassNames()) {
//                        p_.add(new ClassMatching(c.getName()));
//                    }
//                    MethodInfo mloc_ = new MethodInfo();
//                    mloc_.setClassName(clCurName_);
//                    mloc_.setMethodId(m);
//                    mloc_.setParameters(p_);
//                    signatures_.add(mloc_);
//                }
//                signatures_.sortElts(new ParametersGroupComparator<MethodInfo>(gr_));
//                CustList<MethodInfo> errors_ = new CustList<MethodInfo>();
//                for (MethodInfo p : signatures_) {
//                    if (p.getParameters().isError()) {
//                        errors_.add(p);
//                    }
//                }
//                if (!signatures_.first().getParameters().isError()) {
//                    methodId = signatures_.first().getMethodId();
//                    MethodMetaInfo methodMetaInfo_ = custClass_.getMethods().getVal(methodId);
//                    setResultClass(new ClassArgumentMatching(methodMetaInfo_.getReturnType().getName()));
//                    return;
//                }
//                throw new AmbiguousChoiceCallingException(errors_.join(RETURN_LINE)+RETURN_LINE+_conf.joinPages());
//                //                custClass_.getMethods().getVal()
//            }
//        }
//        Method m_ = getDeclaredMethod(_conf, clCur_, trimMeth_, ClassArgumentMatching.toArgArray(firstArgs_));
//        if (!canBeUsed(m_, _conf)) {
//            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//            throw new BadAccessException(m_.toString()+RETURN_LINE+_conf.joinPages());
//        }
//        if (isStaticAccess() && !Modifier.isStatic(m_.getModifiers())) {
//            throw new StaticAccessException(_conf.joinPages());
//        }
//        method = m_;
//        setAccess(method, _conf);
//        setResultClass(new ClassArgumentMatching(m_.getReturnType().getName()));
//    }
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
    @Override
    public void analyzeSetting(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }
    void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        if (StringList.quickEq(trimMeth_, EXTERN_CLASS+CURRENT)) {
            //validate calling of constructors of the current class
//            String clCurName_ = _conf.getLastPage().getGlobalArgument().getArgClassName();
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
//            String clCurName_ = _conf.getLastPage().getGlobalArgument().getArgClassName();
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
//            setResultClass(new ClassArgumentMatching(m_.getReturnType().getName()));
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getAliasArrayClass(m_.getReturnType())));
        }
    }
//    @Override
//    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
//            ContextEl _conf, Calculation _setting) {
//        Classes classes_ = _conf.getClasses();
//        CustList<OperationNode> chidren_ = getChildrenAmong();
//        int off_ = StringList.getFirstPrintableCharIndex(methodName);
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//        String trimMeth_ = methodName.trim();
//        if (constId != null) {
//            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CURRENT)) {
////                String clCurName_ = global_.getArgClassName();
//                String clCurName_ = _conf.getLastPage().getGlobalClass();
//                CustList<Argument> firstArgs_ = listArguments(chidren_, _nodes, false);
//                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
//                called_.add(clCurName_);
//                Argument global_ = _conf.getLastPage().getGlobalArgument();
//                throw new CustomFoundConstructorException(clCurName_, called_, constId, global_, firstArgs_, InstancingStep.USING_THIS);
//            }
//            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+SUPER_ACCESS)) {
////                String clCurName_ = global_.getArgClassName();
//                String clCurName_ = _conf.getLastPage().getGlobalClass();
//                String superClass_ = _conf.getClasses().getClassMetaInfo(clCurName_).getSuperClass();
//                CustList<Argument> firstArgs_ = listArguments(chidren_, _nodes, false);
//                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
//                called_.add(superClass_);
//                _conf.getLastPage().getCurrentEls().clear();
//                Argument global_ = _conf.getLastPage().getGlobalArgument();
//                throw new CustomFoundConstructorException(superClass_, called_, constId, global_, firstArgs_, InstancingStep.USING_SUPER);
//            }
//        }
//        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+INSTANCEOF)) {
//            if (chidren_.size() == 2) {
//                OperationNode oOne_ = chidren_.first();
//                String str_ = (String) _nodes.getVal(oOne_).getArgument().getObject();
//                if (str_ == null) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new NullObjectException(_conf.joinPages());
//                }
//                str_ = StringList.removeAllSpaces(str_);
//                //                Class<?> cl_;
//                //                try {
//                    //                    cl_ = ConstClasses.classForNameNotInit(str_);
//                    //                } catch (RuntimeClassNotFoundException _0_) {
//                        //                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                //                    throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
//                //                }
//                checkExist(_conf, str_, false, true, chidren_.first().getIndexInEl()+1);
//                OperationNode oTwo_ = chidren_.last();
//                Argument sec_ = _nodes.getVal(oTwo_).getArgument();
//                if (sec_.isNull()) {
//                    Argument arg_ = new Argument();
//                    arg_.setArgClassName(getResultClass().getName());
//                    arg_.setObject(false);
//                    setSimpleArgument(arg_, _conf, _nodes);
//                    return arg_;
//                }
//                String className_ = sec_.getStruct().getClassName();
//                //                boolean res_ = cl_.isInstance(_nodes.getVal(oTwo_).getArgument().getObject());
//                boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(str_, className_, classes_);
//                Argument arg_ = new Argument();
//                arg_.setArgClassName(getResultClass().getName());
//                arg_.setObject(res_);
//                setSimpleArgument(arg_, _conf, _nodes);
//                return arg_;
//            }
//        }
//        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CAST)) {
//            if (chidren_.size() == 1) {
//                OperationNode oOne_ = chidren_.first();
//                String str_ = (String) _nodes.getVal(oOne_).getArgument().getObject();
//                if (str_ == null) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new NullObjectException(_conf.joinPages());
//                }
//                str_ = StringList.removeAllSpaces(str_);
//                Class<?> cl_;
//                try {
//                    cl_ = ConstClasses.classForNameNotInit(str_);
//                } catch (RuntimeClassNotFoundException _0_) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
//                }
//                Argument arg_ = new Argument();
//                arg_.setArgClassName(getResultClass().getName());
//                arg_.setObject(cl_);
//                setSimpleArgument(arg_, _conf, _nodes);
//                return arg_;
//            }
//            if (chidren_.size() == 2) {
//                OperationNode oOne_ = chidren_.first();
//                OperationNode oTwo_ = chidren_.last();
//                Argument objArg_ = _nodes.getVal(oTwo_).getArgument();
//                Object o_ = objArg_.getObject();
//                if (o_ == null) {
//                    Argument arg_ = new Argument();
//                    arg_.setArgClassName(getResultClass().getName());
//                    setSimpleArgument(arg_, _conf, _nodes);
//                    return arg_;
//                }
//                //                Class<?> argClass_ = o_.getClass();
//                Argument classArg_ = _nodes.getVal(oOne_).getArgument();
//                //                String argClassName_ = objArg_.getArgClassName();
//                //                String argClassName_ = argClass_.getName();
//                String argClassName_ = objArg_.getObjectClassName();
//                ClassArgumentMatching resCl_ = getResultClass();
//                //                Class<?> param_ = getResultClass().getClazz();
//                //                String paramName_ = resCl_.getName();
//                String paramName_ = (String) classArg_.getObject();
//                //                if (!PrimitiveTypeUtil.canBeUseAsArgument(param_, argClass_)) {
//                //                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
//                //                    throw new DynamicCastClassException(argClass_+RETURN_LINE+param_+RETURN_LINE+_conf.joinPages());
//                //                }
//                if (!resCl_.isPrimitive() || !objArg_.isPrimitiveClass()) {
//                    if (!PrimitiveTypeUtil.canBeUseAsArgument(paramName_, argClassName_, classes_)) {
//                        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
//                        throw new DynamicCastClassException(argClassName_+RETURN_LINE+paramName_+RETURN_LINE+_conf.joinPages());
//                    }
//                }
//                Argument arg_ = new Argument();
//                arg_.setArgClassName(paramName_);
//                arg_.setObject(PrimitiveTypeUtil.convertObject(resCl_, o_));
////                if (resCl_.matchClass(double.class) || resCl_.matchClass(Double.class)) {
////                    arg_.setObject(((Number)o_).doubleValue());
////                } else if (resCl_.matchClass(float.class) || resCl_.matchClass(Float.class)) {
////                    arg_.setObject(((Number)o_).floatValue());
////                } else if (resCl_.matchClass(long.class) || resCl_.matchClass(Long.class)) {
////                    arg_.setObject(((Number)o_).longValue());
////                } else if (resCl_.matchClass(int.class) || resCl_.matchClass(Integer.class)) {
////                    arg_.setObject(((Number)o_).intValue());
////                } else if (resCl_.matchClass(short.class) || resCl_.matchClass(Short.class)) {
////                    arg_.setObject(((Number)o_).shortValue());
////                } else if (resCl_.matchClass(byte.class) || resCl_.matchClass(Byte.class)) {
////                    arg_.setObject(((Number)o_).shortValue());
////                } else if (resCl_.matchClass(char.class) || resCl_.matchClass(Character.class)) {
////                    arg_.setObject(((Character)o_).charValue());
////                } else {
////                    arg_.setObject(o_);
////                }
//                setSimpleArgument(arg_, _conf, _nodes);
//                return arg_;
//            }
//        }
//        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+BOOLEAN)) {
//            OperationNode opOne_ = chidren_.first();
//            Boolean obj_ = (Boolean) _nodes.getVal(opOne_).getArgument().getObject();
//            Argument arg_;
//            if (obj_) {
//                arg_ = _nodes.getVal(chidren_.get(CustList.SECOND_INDEX)).getArgument();
//            } else {
//                arg_ = _nodes.getVal(chidren_.last()).getArgument();
//            }
//            setSimpleArgument(arg_, _conf, _nodes);
//            return arg_;
//        }
//        CustList<Argument> firstArgs_;
//        // = listArguments(chidren_, _nodes);
//        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
//        //        Classes classes_ = _conf.getClasses();
//        //        ClassMetaInfo custClass_ = null;
//        String clCur_ = getPreviousResultClass().getName();
//        if (methodId != null) {
//            firstArgs_ = listArguments(chidren_, _nodes, false);
//            throw new CustomFoundMethodException(arg_, clCur_, methodId, firstArgs_);
//        }
//        firstArgs_ = listArguments(chidren_, _nodes, true);
//        //        if (classes_ != null) {
//        //            custClass_ = classes_.getClassMetaInfo(clCur_);
//        //            if (custClass_ != null) {
//        //                throw new CustomFoundMethodException(clCur_, methodId, firstArgs_);
//        ////                if (_conf.isCallingXml()) {
//        //////                    methodMetaInfo
//        ////                    throw new CustomFoundMethodException(clCur_, methodId, firstArgs_);
//        ////                } else {
//        ////                    Object o_ = ProcessXmlMethod.calculateArgument(clCur_, methodId, firstArgs_, _conf).getObject();
//        ////                    Argument argres_ = new Argument();
//        ////                    // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
//        ////                    argres_.setArgClassName(getResultClass().getName());
//        ////                    argres_.setObject(o_);
//        ////                    setSimpleArgument(argres_, _conf, _nodes);
//        ////                    return argres_;
//        ////                }
//        //            }
//        //        }
//        //        if (arg_.getArgClassName())
//        Object obj_ = arg_.getObject();
//        if (!Modifier.isStatic(method.getModifiers()) && obj_ == null) {
//            throw new NullObjectException(_conf.joinPages());
//        }
//        if (method.getParameterTypes().length == 0) {
//            String lang_ = _conf.getLanguage();
//            if (lang_ != null) {
//                if (StringList.quickEq(lang_, JAVA)) {
//                    if (StringList.quickEq(method.getName(), JAVA_GET_CLASS)) {
//                        Argument argres_ = new Argument();
//                        argres_.setArgClassName(getResultClass().getName());
//                        //                        argres_.setObject(obj_.getClass());
//                        argres_.setObject(ConstClasses.classForNameNotInit(arg_.getObjectClassName()));
//                        setSimpleArgument(argres_, _conf, _nodes);
//                        return argres_;
//                    }
//                }
//                if (StringList.quickEq(lang_, CSHARP)) {
//                    if (StringList.quickEq(method.getName(), CSHARP_GET_TYPE)) {
//                        Argument argres_ = new Argument();
//                        argres_.setArgClassName(getResultClass().getName());
//                        //                        argres_.setObject(obj_.getClass());
//                        argres_.setObject(ConstClasses.classForNameNotInit(arg_.getObjectClassName()));
//                        setSimpleArgument(argres_, _conf, _nodes);
//                        return argres_;
//                    }
//                }
//            }
//        }
//        Struct ret_ = invokeMethod(_conf, 0, clCur_, method, obj_, getObjects(Argument.toArgArray(firstArgs_)));
//        Argument argres_ = new Argument();
//        // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
//        argres_.setArgClassName(getResultClass().getName());
//        argres_.setStruct(ret_);
//        setSimpleArgument(argres_, _conf, _nodes);
//        return argres_;
//    }
    @Override
    public Argument calculateLeft(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }
    
    @Override
    public Argument calculateRight(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    @Override
    public Argument calculateSetting(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }
    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenAmong();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        if (constId != null) {
            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CURRENT)) {
//                String clCurName_ = global_.getArgClassName();
                String clCurName_ = _conf.getLastPage().getGlobalClass();
                CustList<Argument> firstArgs_ = listArguments(chidren_, _nodes, false);
                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
                called_.add(clCurName_);
                Argument global_ = _conf.getLastPage().getGlobalArgument();
                throw new CustomFoundConstructorException(clCurName_, called_, constId, global_, firstArgs_, InstancingStep.USING_THIS);
            }
            if (StringList.quickEq(trimMeth_,EXTERN_CLASS+SUPER_ACCESS)) {
//                String clCurName_ = global_.getArgClassName();
                String clCurName_ = _conf.getLastPage().getGlobalClass();
                String superClass_ = _conf.getClasses().getClassMetaInfo(clCurName_).getSuperClass();
                CustList<Argument> firstArgs_ = listArguments(chidren_, _nodes, false);
                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
                called_.add(superClass_);
                _conf.getLastPage().getCurrentEls().clear();
                Argument global_ = _conf.getLastPage().getGlobalArgument();
                throw new CustomFoundConstructorException(superClass_, called_, constId, global_, firstArgs_, InstancingStep.USING_SUPER);
            }
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+INSTANCEOF)) {
            if (chidren_.size() == 2) {
                OperationNode oOne_ = chidren_.first();
                String str_ = (String) _nodes.getVal(oOne_).getArgument().getObject();
                if (str_ == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NullObjectException(_conf.joinPages());
                }
                str_ = StringList.removeAllSpaces(str_);
                //                Class<?> cl_;
                //                try {
                    //                    cl_ = ConstClasses.classForNameNotInit(str_);
                    //                } catch (RuntimeClassNotFoundException _0_) {
                        //                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                //                    throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
                //                }
                checkExist(_conf, str_, false, true, chidren_.first().getIndexInEl()+1);
                OperationNode oTwo_ = chidren_.last();
                Argument sec_ = _nodes.getVal(oTwo_).getArgument();
                if (sec_.isNull()) {
                    Argument arg_ = new Argument();
                    arg_.setArgClassName(getResultClass().getName());
                    arg_.setObject(false);
                    setSimpleArgument(arg_, _conf, _nodes);
                    return arg_;
                }
                String className_ = sec_.getStruct().getClassName();
                //                boolean res_ = cl_.isInstance(_nodes.getVal(oTwo_).getArgument().getObject());
                boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(str_, className_, classes_);
                Argument arg_ = new Argument();
                arg_.setArgClassName(getResultClass().getName());
                arg_.setObject(res_);
                setSimpleArgument(arg_, _conf, _nodes);
                return arg_;
            }
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CAST)) {
            if (chidren_.size() == 1) {
                OperationNode oOne_ = chidren_.first();
                String str_ = (String) _nodes.getVal(oOne_).getArgument().getObject();
                if (str_ == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NullObjectException(_conf.joinPages());
                }
                str_ = StringList.removeAllSpaces(str_);
                str_ = PrimitiveTypeUtil.getArrayClass(str_);
                Class<?> cl_;
                try {
                    cl_ = ConstClasses.classAliasForNameNotInit(str_);
                } catch (RuntimeClassNotFoundException _0_) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
                }
                Argument arg_ = new Argument();
                arg_.setArgClassName(getResultClass().getName());
                arg_.setObject(cl_);
                setSimpleArgument(arg_, _conf, _nodes);
                return arg_;
            }
            if (chidren_.size() == 2) {
                OperationNode oOne_ = chidren_.first();
                OperationNode oTwo_ = chidren_.last();
                Argument objArg_ = _nodes.getVal(oTwo_).getArgument();
                Object o_ = objArg_.getObject();
                if (o_ == null) {
                    Argument arg_ = new Argument();
                    arg_.setArgClassName(getResultClass().getName());
                    setSimpleArgument(arg_, _conf, _nodes);
                    return arg_;
                }
                //                Class<?> argClass_ = o_.getClass();
                Argument classArg_ = _nodes.getVal(oOne_).getArgument();
                //                String argClassName_ = objArg_.getArgClassName();
                //                String argClassName_ = argClass_.getName();
                String argClassName_ = objArg_.getObjectClassName();
                ClassArgumentMatching resCl_ = getResultClass();
                //                Class<?> param_ = getResultClass().getClazz();
                //                String paramName_ = resCl_.getName();
                String paramName_ = (String) classArg_.getObject();
                String className_ = oTwo_.getResultClass().getName();
                if (className_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    className_ = className_.substring(1);
                }
                //                if (!PrimitiveTypeUtil.canBeUseAsArgument(param_, argClass_)) {
                //                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                //                    throw new DynamicCastClassException(argClass_+RETURN_LINE+param_+RETURN_LINE+_conf.joinPages());
                //                }
//                if (!resCl_.isPrimitive() || !objArg_.isPrimitiveClass())
                if (!resCl_.isPrimitive() || ConstClasses.getPrimitiveClass(className_) == null) {
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(paramName_, argClassName_, classes_)) {
                        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                        throw new DynamicCastClassException(argClassName_+RETURN_LINE+paramName_+RETURN_LINE+_conf.joinPages());
                    }
                }
                Argument arg_ = new Argument();
                arg_.setArgClassName(paramName_);
                arg_.setObject(PrimitiveTypeUtil.convertObject(resCl_, o_));
//                if (resCl_.matchClass(double.class) || resCl_.matchClass(Double.class)) {
//                    arg_.setObject(((Number)o_).doubleValue());
//                } else if (resCl_.matchClass(float.class) || resCl_.matchClass(Float.class)) {
//                    arg_.setObject(((Number)o_).floatValue());
//                } else if (resCl_.matchClass(long.class) || resCl_.matchClass(Long.class)) {
//                    arg_.setObject(((Number)o_).longValue());
//                } else if (resCl_.matchClass(int.class) || resCl_.matchClass(Integer.class)) {
//                    arg_.setObject(((Number)o_).intValue());
//                } else if (resCl_.matchClass(short.class) || resCl_.matchClass(Short.class)) {
//                    arg_.setObject(((Number)o_).shortValue());
//                } else if (resCl_.matchClass(byte.class) || resCl_.matchClass(Byte.class)) {
//                    arg_.setObject(((Number)o_).shortValue());
//                } else if (resCl_.matchClass(char.class) || resCl_.matchClass(Character.class)) {
//                    arg_.setObject(((Character)o_).charValue());
//                } else {
//                    arg_.setObject(o_);
//                }
                setSimpleArgument(arg_, _conf, _nodes);
                return arg_;
            }
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+BOOLEAN)) {
            OperationNode opOne_ = chidren_.first();
            Boolean obj_ = (Boolean) _nodes.getVal(opOne_).getArgument().getObject();
            Argument arg_;
            if (obj_) {
                arg_ = _nodes.getVal(chidren_.get(CustList.SECOND_INDEX)).getArgument();
            } else {
                arg_ = _nodes.getVal(chidren_.last()).getArgument();
            }
            setSimpleArgument(arg_, _conf, _nodes);
            return arg_;
        }
        CustList<Argument> firstArgs_;
        // = listArguments(chidren_, _nodes);
        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
        //        Classes classes_ = _conf.getClasses();
        //        ClassMetaInfo custClass_ = null;
        String clCur_ = getPreviousResultClass().getName();
        if (methodId != null) {
            firstArgs_ = listArguments(chidren_, _nodes, false);
//            String className_ = methodMetaInfo.getClassName();
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
                        argres_.setArgClassName(getResultClass().getName());
                        argres_.setObject(name_);
                        setSimpleArgument(argres_, _conf, _nodes);
                        return argres_;
                    }
                    if (methodId.eq(new FctConstraints(METH_ORDINAL, new EqList<StringList>()))) {
                        CustEnum cen_ = (CustEnum) arg_.getStruct().getInstance();
                        int name_ = cen_.ordinal();
                        Argument argres_ = new Argument();
                        argres_.setArgClassName(PrimitiveTypeUtil.PRIM_INT);
                        argres_.setObject(name_);
                        setSimpleArgument(argres_, _conf, _nodes);
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
                        argres_.setStructArgClassName(new Struct(o_,clArr_));
                        setSimpleArgument(argres_, _conf, _nodes);
                        return argres_;
                    }
                }
                classNameFound_ = classMethodId.getClassName().getName();
            }
            throw new CustomFoundMethodException(arg_, classNameFound_, methodId, firstArgs_);
        }
        firstArgs_ = listArguments(chidren_, _nodes, true);
        //        if (classes_ != null) {
        //            custClass_ = classes_.getClassMetaInfo(clCur_);
        //            if (custClass_ != null) {
        //                throw new CustomFoundMethodException(clCur_, methodId, firstArgs_);
        ////                if (_conf.isCallingXml()) {
        //////                    methodMetaInfo
        ////                    throw new CustomFoundMethodException(clCur_, methodId, firstArgs_);
        ////                } else {
        ////                    Object o_ = ProcessXmlMethod.calculateArgument(clCur_, methodId, firstArgs_, _conf).getObject();
        ////                    Argument argres_ = new Argument();
        ////                    // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
        ////                    argres_.setArgClassName(getResultClass().getName());
        ////                    argres_.setObject(o_);
        ////                    setSimpleArgument(argres_, _conf, _nodes);
        ////                    return argres_;
        ////                }
        //            }
        //        }
        //        if (arg_.getArgClassName())
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
                        argres_.setArgClassName(getResultClass().getName());
                        //                        argres_.setObject(obj_.getClass());
//                        argres_.setObject(ConstClasses.classForNameNotInit(arg_.getObjectClassName()));
                        argres_.setObject(ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(arg_.getObjectClassName())));
                        setSimpleArgument(argres_, _conf, _nodes);
                        return argres_;
                    }
                }
                if (StringList.quickEq(lang_, CSHARP)) {
                    if (StringList.quickEq(method.getName(), CSHARP_GET_TYPE)) {
                        Argument argres_ = new Argument();
                        argres_.setArgClassName(getResultClass().getName());
                        //                        argres_.setObject(obj_.getClass());
                        argres_.setObject(ConstClasses.classAliasForNameNotInit(arg_.getObjectClassName()));
                        setSimpleArgument(argres_, _conf, _nodes);
                        return argres_;
                    }
                }
            }
        }
        Struct ret_ = invokeMethod(_conf, 0, clCur_, method, obj_, getObjects(Argument.toArgArray(firstArgs_)));
        Argument argres_ = new Argument();
        // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
        argres_.setArgClassName(getResultClass().getName());
        argres_.setStruct(ret_);
        setSimpleArgument(argres_, _conf, _nodes);
        return argres_;
    }
    /**@throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException*/
//    @Override
//    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting) {
//        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
//        int off_ = StringList.getFirstPrintableCharIndex(methodName);
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//        String trimMeth_ = methodName.trim();
//        Classes classes_ = _conf.getClasses();
//        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+INSTANCEOF)) {
//            if (chidren_.size() == 2) {
//                String str_ = (String) chidren_.first().getArgument().getObject();
//                if (str_ == null) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new NullObjectException(_conf.joinPages());
//                }
//                str_ = StringList.removeAllSpaces(str_);
//                checkExist(_conf, str_, false, true, chidren_.first().getIndexInEl()+1);
//                Argument sec_ = chidren_.last().getArgument();
//                if (sec_.isNull()) {
//                    Argument arg_ = new Argument();
//                    arg_.setArgClassName(getResultClass().getName());
//                    arg_.setObject(false);
//                    setSimpleArgument(arg_, _conf);
//                    return;
//                }
//                //                Class<?> cl_;
//                //                try {
//                    //                    cl_ = ConstClasses.classForNameNotInit(str_);
//                    //                } catch (RuntimeClassNotFoundException _0_) {
//                        //                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                //                    throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
//                //                }
//                String className_ = sec_.getStruct().getClassName();
//                //              boolean res_ = cl_.isInstance(_nodes.getVal(oTwo_).getArgument().getObject());
//                boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(str_, className_, classes_);
//                Argument arg_ = new Argument();
//                arg_.setArgClassName(getResultClass().getName());
//                arg_.setObject(res_);
//                setSimpleArgument(arg_, _conf);
//                return;
//                //                boolean res_ = cl_.isInstance(chidren_.last().getArgument().getObject());
//                //                Argument arg_ = new Argument();
//                //                arg_.setArgClassName(getResultClass().getName());
//                //                arg_.setObject(res_);
//                //                setSimpleArgument(arg_, _conf);
//                //                return;
//            }
//        }
//        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CAST)) {
//            if (chidren_.size() == 1) {
//                String str_ = (String) chidren_.first().getArgument().getObject();
//                if (str_ == null) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new NullObjectException(_conf.joinPages());
//                }
//                str_ = StringList.removeAllSpaces(str_);
//                Class<?> cl_;
//                try {
//                    cl_ = ConstClasses.classForNameNotInit(str_);
//                } catch (RuntimeClassNotFoundException _0_) {
//                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
//                    throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
//                }
//                Argument arg_ = new Argument();
//                arg_.setArgClassName(getResultClass().getName());
//                arg_.setObject(cl_);
//                setSimpleArgument(arg_, _conf);
//                return;
//            }
//            if (chidren_.size() == 2) {
//                Argument objArg_ = chidren_.last().getArgument();
//                Object o_ = objArg_.getObject();
//                if (o_ == null) {
//                    Argument arg_ = new Argument();
//                    arg_.setArgClassName(getResultClass().getName());
//                    setSimpleArgument(arg_, _conf);
//                    return;
//                }
//                //                Class<?> argClass_ = o_.getClass();
//                Argument classArg_ = chidren_.first().getArgument();
//                //                String argClassName_ = objArg_.getArgClassName();
//                //                String argClassName_ = argClass_.getName();
//                String argClassName_ = objArg_.getObjectClassName();
//                ClassArgumentMatching resCl_ = getResultClass();
//                //                Class<?> param_ = getResultClass().getClazz();
//                //                String paramName_ = resCl_.getName();
//                String paramName_ = (String) classArg_.getObject();
//                //                if (!PrimitiveTypeUtil.canBeUseAsArgument(param_, argClass_)) {
//                //                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
//                //                    throw new DynamicCastClassException(argClass_+RETURN_LINE+param_+RETURN_LINE+_conf.joinPages());
//                //                }
//                if (!resCl_.isPrimitive() || !objArg_.isPrimitiveClass()) {
//                    if (!PrimitiveTypeUtil.canBeUseAsArgument(paramName_, argClassName_, classes_)) {
//                        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
//                        throw new DynamicCastClassException(argClassName_+RETURN_LINE+paramName_+RETURN_LINE+_conf.joinPages());
//                    }
//                }
//                Argument arg_ = new Argument();
//                arg_.setArgClassName(paramName_);
//                arg_.setObject(PrimitiveTypeUtil.convertObject(resCl_, o_));
////                if (resCl_.matchClass(double.class) || resCl_.matchClass(Double.class)) {
////                    arg_.setObject(((Number)o_).doubleValue());
////                } else if (resCl_.matchClass(float.class) || resCl_.matchClass(Float.class)) {
////                    arg_.setObject(((Number)o_).floatValue());
////                } else if (resCl_.matchClass(long.class) || resCl_.matchClass(Long.class)) {
////                    arg_.setObject(((Number)o_).longValue());
////                } else if (resCl_.matchClass(int.class) || resCl_.matchClass(Integer.class)) {
////                    arg_.setObject(((Number)o_).intValue());
////                } else if (resCl_.matchClass(short.class) || resCl_.matchClass(Short.class)) {
////                    arg_.setObject(((Number)o_).shortValue());
////                } else if (resCl_.matchClass(byte.class) || resCl_.matchClass(Byte.class)) {
////                    arg_.setObject(((Number)o_).shortValue());
////                } else if (resCl_.matchClass(char.class) || resCl_.matchClass(Character.class)) {
////                    arg_.setObject(((Character)o_).charValue());
////                } else {
////                    arg_.setObject(o_);
////                }
//                setSimpleArgument(arg_, _conf);
//                return;
//            }
//        }
//        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+BOOLEAN)) {
//            OperationNode opOne_ = chidren_.first();
//            Boolean obj_ = (Boolean) opOne_.getArgument().getObject();
//            Argument arg_;
//            if (obj_) {
//                arg_ = chidren_.get(CustList.SECOND_INDEX).getArgument();
//            } else {
//                arg_ = chidren_.last().getArgument();
//            }
//            setSimpleArgument(arg_, _conf);
//            return;
//        }
//        CustList<Argument> firstArgs_;
//        // = listArguments(chidren_);
//        Argument arg_ = getPreviousArgument();
//        String clCur_ = getPreviousResultClass().getName();
//        if (methodId != null) {
//            firstArgs_ = listArguments(chidren_, false);
//            Struct o_ = ProcessXmlMethod.calculateArgument(arg_, clCur_, methodId, firstArgs_, _conf).getStruct();
//            Argument argres_ = new Argument();
//            // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
//            argres_.setArgClassName(getResultClass().getName());
//            argres_.setStruct(o_);
//            setSimpleArgument(argres_, _conf);
//            return;
//        }
//        firstArgs_ = listArguments(chidren_, true);
//        //        Classes classes_ = _conf.getClasses();
//        //        ClassMetaInfo custClass_ = null;
//        //        if (classes_ != null) {
//        //            custClass_ = classes_.getClassMetaInfo(clCur_);
//        //            if (custClass_ != null) {
//        ////                if (_conf.isCallingXml()) {
//        //////                    methodMetaInfo
//        ////                    throw new CustomFoundMethodException(clCur_, methodId, firstArgs_);
//        ////                } else {
//        ////                    Object o_ = ProcessXmlMethod.calculateArgument(clCur_, methodId, firstArgs_, _conf).getObject();
//        ////                    Argument argres_ = new Argument();
//        ////                    // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
//        ////                    argres_.setArgClassName(getResultClass().getName());
//        ////                    argres_.setObject(o_);
//        ////                    setSimpleArgument(argres_, _conf);
//        ////                    return;
//        ////                }
//        //                Object o_ = ProcessXmlMethod.calculateArgument(clCur_, methodId, firstArgs_, _conf).getObject();
//        //                Argument argres_ = new Argument();
//        //                // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
//        //                argres_.setArgClassName(getResultClass().getName());
//        //                argres_.setObject(o_);
//        //                setSimpleArgument(argres_, _conf);
//        //                return;
//        //            }
//        //        }
//        //        if (arg_.getArgClassName())
//        Object obj_ = arg_.getObject();
//        if (!Modifier.isStatic(method.getModifiers()) && obj_ == null) {
//            throw new NullObjectException(_conf.joinPages());
//        }
//        if (method.getParameterTypes().length == 0) {
//            String lang_ = _conf.getLanguage();
//            if (lang_ != null) {
//                if (StringList.quickEq(lang_, JAVA)) {
//                    if (StringList.quickEq(method.getName(), JAVA_GET_CLASS)) {
//                        Argument argres_ = new Argument();
//                        argres_.setArgClassName(getResultClass().getName());
//                        //                        argres_.setObject(obj_.getClass());
//                        argres_.setObject(ConstClasses.classForNameNotInit(arg_.getObjectClassName()));
//                        setSimpleArgument(argres_, _conf);
//                        return;
//                    }
//                }
//                if (StringList.quickEq(lang_, CSHARP)) {
//                    if (StringList.quickEq(method.getName(), CSHARP_GET_TYPE)) {
//                        Argument argres_ = new Argument();
//                        argres_.setArgClassName(getResultClass().getName());
//                        //                        argres_.setObject(obj_.getClass());
//                        argres_.setObject(ConstClasses.classForNameNotInit(arg_.getObjectClassName()));
//                        setSimpleArgument(argres_, _conf);
//                        return;
//                    }
//                }
//            }
//        }
//        Struct ret_ = invokeMethod(_conf, 0, clCur_, method, obj_, getObjects(Argument.toArgArray(firstArgs_)));
//        Argument argres_ = new Argument();
//        // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
//        argres_.setArgClassName(getResultClass().getName());
//        argres_.setStruct(ret_);
//        setSimpleArgument(argres_, _conf);
//
//        //        if (!chidren_.isEmpty() && chidren_.first().isVararg()) {
//        ////            chidren_.first().
//        ////            Argument[] args_ = new Argument[chidren_.size()];
//        //            CustList<Argument> firstArgs_ = new CustList<Argument>();
//        //            CustList<Argument> optArgs_ = new CustList<Argument>();
//        //            boolean opt_ = false;
//        //            for (OperationNode o: chidren_) {
//        //                if (o.isVararg()) {
//        //                    continue;
//        //                }
//        //                if (o.isFirstOptArg()) {
//        //                    opt_ = true;
//        //                }
//        //                if (opt_) {
//        //                    optArgs_.add(o.getArgument());
//        //                } else {
//        //                    firstArgs_.add(o.getArgument());
//        //                }
//        //            }
//        //            Class<?> cl_ = chidren_.first().getArgument().getArgClass();
//        //            Object array_ = Array.newInstance(cl_, optArgs_.size());
//        //            int len_ = optArgs_.size();
//        //            for (int i = 0; i < len_; i++) {
//        //                Array.set(array_, i, optArgs_.get(i).getObject());
//        //            }
//        //            Argument argRem_ = new Argument();
//        //            argRem_.setArgClass(array_.getClass());
//        //            argRem_.setObject(array_);
//        //            firstArgs_.add(argRem_);
//        //            Argument arg_ = getPreviousArgument();
//        //            Class<?> clCur_ = arg_.getArgClass();
//        //            Object obj_ = arg_.getObject();
//        //            setArgument(getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0])));
//        //            return;
//        //        }
//        //        Argument[] args_ = new Argument[chidren_.size()];
//        //        int i_ = CustList.FIRST_INDEX;
//        //        for (OperationNode o: chidren_) {
//        //            args_[i_] = o.getArgument();
//        //            i_++;
//        //        }
//        //        Argument arg_ = getPreviousArgument();
//        //        Class<?> cl_ = arg_.getArgClass();
//        //        Object obj_ = arg_.getObject();
//        //        setArgument(getMethodThenInvoke(_conf, 0, obj_, cl_, methodName, args_));
//        //        SerializeXmlObject.getDeclaredMethod(cl_, methodName, _argsClass);
//    }

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
    @Override
    public void calculateSetting(CustList<OperationNode> _nodes,
            ContextEl _conf, String _op) {
        calculateCommon(_nodes, _conf, _op);
    }
    void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        Classes classes_ = _conf.getClasses();
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+INSTANCEOF)) {
            if (chidren_.size() == 2) {
                String str_ = (String) chidren_.first().getArgument().getObject();
                if (str_ == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NullObjectException(_conf.joinPages());
                }
                str_ = StringList.removeAllSpaces(str_);
                checkExist(_conf, str_, false, true, chidren_.first().getIndexInEl()+1);
                Argument sec_ = chidren_.last().getArgument();
                if (sec_.isNull()) {
                    Argument arg_ = new Argument();
                    arg_.setArgClassName(getResultClass().getName());
                    arg_.setObject(false);
                    setSimpleArgument(arg_, _conf);
                    return;
                }
                //                Class<?> cl_;
                //                try {
                    //                    cl_ = ConstClasses.classForNameNotInit(str_);
                    //                } catch (RuntimeClassNotFoundException _0_) {
                        //                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                //                    throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
                //                }
                String className_ = sec_.getStruct().getClassName();
                //              boolean res_ = cl_.isInstance(_nodes.getVal(oTwo_).getArgument().getObject());
                boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(str_, className_, classes_);
                Argument arg_ = new Argument();
                arg_.setArgClassName(getResultClass().getName());
                arg_.setObject(res_);
                setSimpleArgument(arg_, _conf);
                return;
                //                boolean res_ = cl_.isInstance(chidren_.last().getArgument().getObject());
                //                Argument arg_ = new Argument();
                //                arg_.setArgClassName(getResultClass().getName());
                //                arg_.setObject(res_);
                //                setSimpleArgument(arg_, _conf);
                //                return;
            }
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+CAST)) {
            if (chidren_.size() == 1) {
                String str_ = (String) chidren_.first().getArgument().getObject();
                if (str_ == null) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new NullObjectException(_conf.joinPages());
                }
                str_ = StringList.removeAllSpaces(str_);
                str_ = PrimitiveTypeUtil.getArrayClass(str_);
                Class<?> cl_;
                try {
                    cl_ = ConstClasses.classAliasForNameNotInit(str_);
                } catch (RuntimeClassNotFoundException _0_) {
                    setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl()+1, _conf);
                    throw new RuntimeClassNotFoundException(str_+RETURN_LINE+_conf.joinPages());
                }
                Argument arg_ = new Argument();
                arg_.setArgClassName(getResultClass().getName());
                arg_.setObject(cl_);
                setSimpleArgument(arg_, _conf);
                return;
            }
            if (chidren_.size() == 2) {
                Argument objArg_ = chidren_.last().getArgument();
                Object o_ = objArg_.getObject();
                if (o_ == null) {
                    Argument arg_ = new Argument();
                    arg_.setArgClassName(getResultClass().getName());
                    setSimpleArgument(arg_, _conf);
                    return;
                }
                //                Class<?> argClass_ = o_.getClass();
                Argument classArg_ = chidren_.first().getArgument();
                //                String argClassName_ = objArg_.getArgClassName();
                //                String argClassName_ = argClass_.getName();
                String argClassName_ = objArg_.getObjectClassName();
                ClassArgumentMatching resCl_ = getResultClass();
                //                Class<?> param_ = getResultClass().getClazz();
                //                String paramName_ = resCl_.getName();
                String paramName_ = (String) classArg_.getObject();
                //                if (!PrimitiveTypeUtil.canBeUseAsArgument(param_, argClass_)) {
                //                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                //                    throw new DynamicCastClassException(argClass_+RETURN_LINE+param_+RETURN_LINE+_conf.joinPages());
                //                }
                String className_ = chidren_.last().getResultClass().getName();
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
                arg_.setArgClassName(paramName_);
                arg_.setObject(PrimitiveTypeUtil.convertObject(resCl_, o_));
//                if (resCl_.matchClass(double.class) || resCl_.matchClass(Double.class)) {
//                    arg_.setObject(((Number)o_).doubleValue());
//                } else if (resCl_.matchClass(float.class) || resCl_.matchClass(Float.class)) {
//                    arg_.setObject(((Number)o_).floatValue());
//                } else if (resCl_.matchClass(long.class) || resCl_.matchClass(Long.class)) {
//                    arg_.setObject(((Number)o_).longValue());
//                } else if (resCl_.matchClass(int.class) || resCl_.matchClass(Integer.class)) {
//                    arg_.setObject(((Number)o_).intValue());
//                } else if (resCl_.matchClass(short.class) || resCl_.matchClass(Short.class)) {
//                    arg_.setObject(((Number)o_).shortValue());
//                } else if (resCl_.matchClass(byte.class) || resCl_.matchClass(Byte.class)) {
//                    arg_.setObject(((Number)o_).shortValue());
//                } else if (resCl_.matchClass(char.class) || resCl_.matchClass(Character.class)) {
//                    arg_.setObject(((Character)o_).charValue());
//                } else {
//                    arg_.setObject(o_);
//                }
                setSimpleArgument(arg_, _conf);
                return;
            }
        }
        if (StringList.quickEq(trimMeth_,EXTERN_CLASS+BOOLEAN)) {
            OperationNode opOne_ = chidren_.first();
            Boolean obj_ = (Boolean) opOne_.getArgument().getObject();
            Argument arg_;
            if (obj_) {
                arg_ = chidren_.get(CustList.SECOND_INDEX).getArgument();
            } else {
                arg_ = chidren_.last().getArgument();
            }
            setSimpleArgument(arg_, _conf);
            return;
        }
        CustList<Argument> firstArgs_;
        // = listArguments(chidren_);
        Argument arg_ = getPreviousArgument();
        String clCur_ = getPreviousResultClass().getName();
        if (methodId != null) {
            firstArgs_ = listArguments(chidren_, false);
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
                        argres_.setArgClassName(getResultClass().getName());
                        argres_.setObject(name_);
                        setSimpleArgument(argres_, _conf);
                        return;
                    }
                    if (methodId.eq(new FctConstraints(METH_ORDINAL, new EqList<StringList>()))) {
                        CustEnum cen_ = (CustEnum) arg_.getStruct().getInstance();
                        int name_ = cen_.ordinal();
                        Argument argres_ = new Argument();
                        argres_.setArgClassName(PrimitiveTypeUtil.PRIM_INT);
                        argres_.setObject(name_);
                        setSimpleArgument(argres_, _conf);
                        return;
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
                        argres_.setStructArgClassName(new Struct(o_,clArr_));
                        setSimpleArgument(argres_, _conf);
                        return;
                    }
                }
                classNameFound_ = classMethodId.getClassName().getName();
            }
//            Struct o_ = ProcessXmlMethod.calculateArgument(arg_, clCur_, methodId, firstArgs_, _conf).getStruct();
            Argument argres_ = ProcessXmlMethod.calculateArgument(arg_, classNameFound_, methodId, firstArgs_, _conf);
            // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
//            argres_.setArgClassName(getResultClass().getName());
//            argres_.setStruct(o_);
            setSimpleArgument(argres_, _conf);
            return;
        }
        firstArgs_ = listArguments(chidren_, true);
        //        Classes classes_ = _conf.getClasses();
        //        ClassMetaInfo custClass_ = null;
        //        if (classes_ != null) {
        //            custClass_ = classes_.getClassMetaInfo(clCur_);
        //            if (custClass_ != null) {
        ////                if (_conf.isCallingXml()) {
        //////                    methodMetaInfo
        ////                    throw new CustomFoundMethodException(clCur_, methodId, firstArgs_);
        ////                } else {
        ////                    Object o_ = ProcessXmlMethod.calculateArgument(clCur_, methodId, firstArgs_, _conf).getObject();
        ////                    Argument argres_ = new Argument();
        ////                    // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
        ////                    argres_.setArgClassName(getResultClass().getName());
        ////                    argres_.setObject(o_);
        ////                    setSimpleArgument(argres_, _conf);
        ////                    return;
        ////                }
        //                Object o_ = ProcessXmlMethod.calculateArgument(clCur_, methodId, firstArgs_, _conf).getObject();
        //                Argument argres_ = new Argument();
        //                // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
        //                argres_.setArgClassName(getResultClass().getName());
        //                argres_.setObject(o_);
        //                setSimpleArgument(argres_, _conf);
        //                return;
        //            }
        //        }
        //        if (arg_.getArgClassName())
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
                        argres_.setArgClassName(getResultClass().getName());
                        //                        argres_.setObject(obj_.getClass());
//                        argres_.setObject(ConstClasses.classForNameNotInit(arg_.getObjectClassName()));
                        argres_.setObject(ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(arg_.getObjectClassName())));
                        setSimpleArgument(argres_, _conf);
                        return;
                    }
                }
                if (StringList.quickEq(lang_, CSHARP)) {
                    if (StringList.quickEq(method.getName(), CSHARP_GET_TYPE)) {
                        Argument argres_ = new Argument();
                        argres_.setArgClassName(getResultClass().getName());
                        //                        argres_.setObject(obj_.getClass());
                        argres_.setObject(ConstClasses.classAliasForNameNotInit(arg_.getObjectClassName()));
                        setSimpleArgument(argres_, _conf);
                        return;
                    }
                }
            }
        }
        Struct ret_ = invokeMethod(_conf, 0, clCur_, method, obj_, getObjects(Argument.toArgArray(firstArgs_)));
        Argument argres_ = new Argument();
        // = getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0]));
        argres_.setArgClassName(getResultClass().getName());
        argres_.setStruct(ret_);
        setSimpleArgument(argres_, _conf);

        //        if (!chidren_.isEmpty() && chidren_.first().isVararg()) {
        ////            chidren_.first().
        ////            Argument[] args_ = new Argument[chidren_.size()];
        //            CustList<Argument> firstArgs_ = new CustList<Argument>();
        //            CustList<Argument> optArgs_ = new CustList<Argument>();
        //            boolean opt_ = false;
        //            for (OperationNode o: chidren_) {
        //                if (o.isVararg()) {
        //                    continue;
        //                }
        //                if (o.isFirstOptArg()) {
        //                    opt_ = true;
        //                }
        //                if (opt_) {
        //                    optArgs_.add(o.getArgument());
        //                } else {
        //                    firstArgs_.add(o.getArgument());
        //                }
        //            }
        //            Class<?> cl_ = chidren_.first().getArgument().getArgClass();
        //            Object array_ = Array.newInstance(cl_, optArgs_.size());
        //            int len_ = optArgs_.size();
        //            for (int i = 0; i < len_; i++) {
        //                Array.set(array_, i, optArgs_.get(i).getObject());
        //            }
        //            Argument argRem_ = new Argument();
        //            argRem_.setArgClass(array_.getClass());
        //            argRem_.setObject(array_);
        //            firstArgs_.add(argRem_);
        //            Argument arg_ = getPreviousArgument();
        //            Class<?> clCur_ = arg_.getArgClass();
        //            Object obj_ = arg_.getObject();
        //            setArgument(getMethodThenInvoke(_conf, 0, obj_, clCur_, methodName, firstArgs_.toArray(new Argument[0])));
        //            return;
        //        }
        //        Argument[] args_ = new Argument[chidren_.size()];
        //        int i_ = CustList.FIRST_INDEX;
        //        for (OperationNode o: chidren_) {
        //            args_[i_] = o.getArgument();
        //            i_++;
        //        }
        //        Argument arg_ = getPreviousArgument();
        //        Class<?> cl_ = arg_.getArgClass();
        //        Object obj_ = arg_.getObject();
        //        setArgument(getMethodThenInvoke(_conf, 0, obj_, cl_, methodName, args_));
        //        SerializeXmlObject.getDeclaredMethod(cl_, methodName, _argsClass);
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
