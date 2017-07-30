package code.expressionlanguage.opers;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadIndexTypeException;
import code.expressionlanguage.exceptions.CustomFoundConstructorException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.EmptyArrayDimensionsException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.IllegalClassConstructorException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.NegativeSizeException;
import code.expressionlanguage.exceptions.NotArrayException;
import code.expressionlanguage.exceptions.NotInitializedClassException;
import code.expressionlanguage.exceptions.NullGlobalObjectException;
import code.expressionlanguage.exceptions.PrimitiveTypeException;
import code.expressionlanguage.exceptions.SettingMemberException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.ProcessXmlMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.FctConstraints;
import code.expressionlanguage.opers.util.Struct;
import code.serialize.exceptions.BadAccessException;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class InstanceOperation extends InvokingOperation {

//    private static final String BOOLEAN = "Z";
//    private static final String CHAR = "C";
//    private static final String BYTE = "B";
//    private static final String SHORT = "S";
//    private static final String INTEGER = "I";
//    private static final String LONG = "J";
//    private static final String FLOAT = "F";
//    private static final String DOUBLE = "D";
//    private static final String SUFFIX_INSTANCE = ";";

    private boolean possibleInitClass;

    private String methodName;
//    private boolean needPrevious;
    private Constructor<?> contructor;

    private FctConstraints constId;

    public InstanceOperation(String _el, int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

//    @Override
//    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting) {
//        if (_setting.getStep()  == StepCalculation.LEFT && getParent() == null) {
//            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//            throw new SettingMemberException(_conf.joinPages());
//        }
//        Classes classes_ = _conf.getClasses();
//        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
//        int off_ = StringList.getFirstPrintableCharIndex(methodName);
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//        String className_ = methodName.trim().substring(INSTANCE.length()+2);
//        className_ = StringList.removeAllSpaces(className_);
//        if (!className_.startsWith(ARR) && className_.endsWith(ARR_DYN)) {
//            int len_ = className_.length();
//            throw new NotArrayException(className_.substring(0, len_-ARR_DYN.length())+RETURN_LINE+_conf.joinPages());
//        }
//        boolean elts_ = false;
//        String realClassName_;
//        if (className_.endsWith(ARR_DYN)) {
//            elts_ = true;
//            int len_ = className_.length();
//            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
//        } else {
//            realClassName_ = className_;
//        }
//        if (realClassName_.startsWith(ARR) && realClassName_.endsWith(SUFFIX_INSTANCE)) {
//            if (chidren_.isEmpty() && !elts_) {
//                throw new EmptyArrayDimensionsException(realClassName_+RETURN_LINE+_conf.joinPages());
//            }
////            int[] args_ = new int[chidren_.size()];
//            if (!elts_) {
//                for (OperationNode o: chidren_) {
//                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
//                    if (!o.getResultClass().isNumericInt()) {
//                        ClassArgumentMatching cl_ = o.getResultClass();
//                        throw new BadIndexTypeException(cl_+RETURN_LINE+_conf.joinPages());
//                    }
//                }
//            } else {
//                String eltType_ = PrimitiveTypeUtil.getComponentType(realClassName_);
//                for (OperationNode o: chidren_) {
//                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
//                    String argType_ = o.getResultClass().getName();
//                    if (!PrimitiveTypeUtil.canBeUseAsArgument(eltType_, argType_, classes_)) {
//                        throw new DynamicCastClassException(argType_+RETURN_LINE+eltType_+RETURN_LINE+_conf.joinPages());
//                    }
//                }
//            }
//            realClassName_ = realClassName_.substring(ARR.length());
//            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
//                realClassName_ = realClassName_.substring(ARR.length());
//                realClassName_ = StringList.removeStrings(realClassName_, SUFFIX_INSTANCE);
//            }
//            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//            checkExist(_conf, realClassName_, true, false, 0);
////            Class<?> cl_;
////            try {
////                cl_ = ConstClasses.classForNameNotInit(realClassName_);
////                if (cl_ == void.class) {
////                    throw new VoidArgumentException(_conf.joinPages());
////                }
////            } catch (RuntimeClassNotFoundException _0_) {
////                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
////            }
////            Object o_ = Array.newInstance(cl_, args_);
////            setResultClass(new ClassArgumentMatching(o_.getClass().getName()));
//            if (!elts_) {
//                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getArrayType(realClassName_, chidren_.size())));
//                return;
//            }
//            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getArrayType(realClassName_, CustList.ONE_ELEMENT)));
//            return;
//        }
//        if (realClassName_.startsWith(ARR)) {
//            /*
//            boolean       Z
//            byte       B
//            char       C
//            class or interface       Lclassname;
//            double       D
//            float       F
//            int       I
//            long       J
//            short       S
//            */
//            if (chidren_.isEmpty() && !elts_) {
//                throw new EmptyArrayDimensionsException(realClassName_+RETURN_LINE+_conf.joinPages());
//            }
////            int[] args_ = new int[chidren_.size()];
//            if (!elts_) {
//                for (OperationNode o: chidren_) {
//                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
//                    if (!o.getResultClass().isNumericInt()) {
//                        ClassArgumentMatching cl_ = o.getResultClass();
//                        throw new BadIndexTypeException(cl_+RETURN_LINE+_conf.joinPages());
//                    }
//                }
//            } else {
//                String eltType_ = PrimitiveTypeUtil.getComponentType(realClassName_);
//                for (OperationNode o: chidren_) {
//                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
//                    String argType_ = o.getResultClass().getName();
//                    if (!PrimitiveTypeUtil.canBeUseAsArgument(eltType_, argType_, classes_)) {
//                        throw new DynamicCastClassException(argType_+RETURN_LINE+eltType_+RETURN_LINE+_conf.joinPages());
//                    }
//                }
//            }
//            realClassName_ = realClassName_.substring(ARR.length());
//            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
////                if (StringList.quickEq(realClassName_,DOUBLE)) {
////                    realClassName_ = double.class.getName();
////                } else if (StringList.quickEq(realClassName_,FLOAT)) {
////                    realClassName_ = float.class.getName();
////                } else if (StringList.quickEq(realClassName_,LONG)) {
////                    realClassName_ = long.class.getName();
////                } else if (StringList.quickEq(realClassName_,INTEGER)) {
////                    realClassName_ = int.class.getName();
////                } else if (StringList.quickEq(realClassName_,SHORT)) {
////                    realClassName_ = short.class.getName();
////                } else if (StringList.quickEq(realClassName_,BYTE)) {
////                    realClassName_ = byte.class.getName();
////                } else if (StringList.quickEq(realClassName_,CHAR)) {
////                    realClassName_ = char.class.getName();
////                } else if (StringList.quickEq(realClassName_,BOOLEAN)) {
////                    realClassName_ = boolean.class.getName();
////                }
//                realClassName_ = PrimitiveTypeUtil.getAliasClass(realClassName_);
//            }
//            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//            checkExist(_conf, realClassName_, true, false, 0);
////            Class<?> cl_;
////            try {
////                cl_ = ConstClasses.classForNameNotInit(realClassName_);
////                if (cl_ == void.class) {
////                    throw new VoidArgumentException(_conf.joinPages());
////                }
////            } catch (RuntimeClassNotFoundException _0_) {
////                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
////            }
////            Object o_ = Array.newInstance(cl_, args_);
////            setResultClass(new ClassArgumentMatching(o_.getClass().getName()));
//            if (!elts_) {
//                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getArrayType(realClassName_, chidren_.size())));
//                return;
//            }
//            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getArrayType(realClassName_, CustList.ONE_ELEMENT)));
//            return;
//        }
//        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
////        boolean callIntern_ = false;
//        realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//        if (isFirstChild() || !(getParent() instanceof DotOperation)) {
////            Argument[] args_ = new Argument[chidren_.size()];
////            int i_ = CustList.FIRST_INDEX;
////            for (OperationNode o: chidren_) {
////                args_[i_] = o.getArgument();
////                i_++;
////            }
////            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            boolean intern_ = false;
//            if (StringList.isWord(realClassName_)) {
//                setNeedPrevious(true);
//                setResetablePreviousArg(true);
//                ClassArgumentMatching arg_ = getPreviousResultClass();
//                if (arg_ == null) {
//                    throw new NullGlobalObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
//                }
//                //TODO wrap getDeclaredClasses
//                for (Class<?> c:arg_.getDeclaredClasses()) {
//                    if (StringList.quickEq(c.getSimpleName(), realClassName_)) {
//                        intern_ = true;
//                        firstArgs_.add(CustList.FIRST_INDEX, arg_);
//                        realClassName_ = arg_.getName()+INTERN_CLASS+realClassName_;
//                        break;
//                    }
//                }
//            }
//            constId = getDeclaredCustConstructor(_conf, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(firstArgs_));
//            if (constId != null) {
//                possibleInitClass = true;
//                setResultClass(new ClassArgumentMatching(realClassName_));
//                return;
//            }
//            Class<?> cl_;
//            try {
//                if (StringList.quickEq(realClassName_, OperationNode.VOID_RETURN)) {
//                    throw new VoidArgumentException(_conf.joinPages());
//                }
//                cl_ = ConstClasses.classForNameNotInit(realClassName_);
////                if (cl_ == void.class) {
////                    throw new VoidArgumentException(_conf.joinPages());
////                }
//                if (cl_.isPrimitive()) {
//                    throw new PrimitiveTypeException(realClassName_+RETURN_LINE+_conf.joinPages());
//                }
//            } catch (RuntimeClassNotFoundException _0_) {
//                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
//            }
//            if (cl_.isEnum()) {
//                throw new IllegalClassConstructorException(realClassName_+RETURN_LINE+_conf.joinPages());
//            }
//            if (isStaticAccess() && !Modifier.isStatic(cl_.getModifiers()) && intern_) {
//                throw new StaticAccessException(_conf.joinPages());
//            }
//            Constructor<?> const_ = getDeclaredConstructor(_conf, 0, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(firstArgs_));
//            if (!canBeUsed(const_, _conf)) {
//                throw new BadAccessException(const_+RETURN_LINE+_conf.joinPages());
//            }
//
//            contructor = const_;
//            setAccess(contructor, _conf);
//            setResultClass(new ClassArgumentMatching(realClassName_));
//            return;
//        }
//        setNeedPrevious(true);
//        setResetablePreviousArg(true);
////        Class<?> cl_ = arg_.getArgClass();
////        Object obj_ = arg_.getObject();
//        ClassArgumentMatching arg_ = getPreviousResultClass();
//        if (arg_ == null) {
//            throw new NullGlobalObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
//        }
//        firstArgs_.add(CustList.FIRST_INDEX, arg_);
////        Argument[] args_ = new Argument[chidren_.size()+1];
////        args_[CustList.FIRST_INDEX] = arg_;
////        int i_ = CustList.SECOND_INDEX;
////        for (OperationNode o: chidren_) {
////            args_[i_] = o.getArgument();
////            i_++;
////        }
////        realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//        realClassName_ = arg_.getName()+INTERN_CLASS+realClassName_;
//        constId = getDeclaredCustConstructor(_conf, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(firstArgs_));
//        if (constId != null) {
//            possibleInitClass = true;
//            setResultClass(new ClassArgumentMatching(realClassName_));
//            return;
//        }
//        Class<?> cl_;
//        try {
//            if (StringList.quickEq(realClassName_, OperationNode.VOID_RETURN)) {
//                throw new VoidArgumentException(_conf.joinPages());
//            }
//            cl_ = ConstClasses.classForNameNotInit(realClassName_);
////            if (cl_ == void.class) {
////                throw new VoidArgumentException(_conf.joinPages());
////            }
//            if (cl_.isPrimitive()) {
//                throw new PrimitiveTypeException(realClassName_+RETURN_LINE+_conf.joinPages());
//            }
//        } catch (RuntimeClassNotFoundException _0_) {
//            throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
//        }
//        if (cl_.isEnum()) {
//            throw new IllegalClassConstructorException(cl_.getName()+RETURN_LINE+_conf.joinPages());
//        }
//        if (isStaticAccess() && !Modifier.isStatic(cl_.getModifiers())) {
//            throw new StaticAccessException(_conf.joinPages());
//        }
//        Constructor<?> const_ = getDeclaredConstructor(_conf, 0, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(firstArgs_));
//        if (!canBeUsed(const_, _conf)) {
//            throw new BadAccessException(const_+RETURN_LINE+_conf.joinPages());
//        }
//        contructor = const_;
//        setAccess(contructor, _conf);
//        setResultClass(new ClassArgumentMatching(realClassName_));
//    }

    @Override
    public void analyzeLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        if (getParent() == null) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new SettingMemberException(_conf.joinPages());
        }
        analyzeCommon(_nodes, _conf, _enumContext, _op);
    }

    @Override
    public void analyzeRight(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _enumContext, _op);
    }

    @Override
    public void analyzeSetting(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _enumContext, _op);
    }

    void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, boolean _enumContext, String _op) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = methodName.trim().substring(INSTANCE.length()+2);
        className_ = StringList.removeAllSpaces(className_);
        if (!className_.startsWith(ARR) && className_.endsWith(ARR_DYN)) {
            int len_ = className_.length();
            throw new NotArrayException(className_.substring(0, len_-ARR_DYN.length())+RETURN_LINE+_conf.joinPages());
        }
        boolean elts_ = false;
        String realClassName_;
        if (className_.endsWith(ARR_DYN)) {
            elts_ = true;
            int len_ = className_.length();
            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
        } else {
            realClassName_ = className_;
        }
//        if (realClassName_.startsWith(ARR) && realClassName_.endsWith(SUFFIX_INSTANCE))
        if (realClassName_.startsWith(ARR) && realClassName_.contains(DOT)) {
            if (chidren_.isEmpty() && !elts_) {
                throw new EmptyArrayDimensionsException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
            if (!elts_) {
                for (OperationNode o: chidren_) {
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    if (!o.getResultClass().isNumericInt()) {
                        ClassArgumentMatching cl_ = o.getResultClass();
                        throw new BadIndexTypeException(cl_+RETURN_LINE+_conf.joinPages());
                    }
                }
            } else {
//                String eltType_ = PrimitiveTypeUtil.getComponentType(realClassName_);
                String eltType_ = PrimitiveTypeUtil.getQuickComponentType(realClassName_);
                for (OperationNode o: chidren_) {
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    String argType_ = o.getResultClass().getName();
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(eltType_, argType_, classes_)) {
                        throw new DynamicCastClassException(argType_+RETURN_LINE+eltType_+RETURN_LINE+_conf.joinPages());
                    }
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
//                realClassName_ = realClassName_.substring(ARR.length());
////                realClassName_ = StringList.removeStrings(realClassName_, SUFFIX_INSTANCE);
//            }
            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//            String nativeClassName_ = PrimitiveTypeUtil.getArrayClass(realClassName_);
            checkExist(_conf, realClassName_, true, false, 0);
            if (!elts_) {
//                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getArrayType(realClassName_, chidren_.size())));
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, chidren_.size())));
                return;
            }
//            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getArrayType(realClassName_, CustList.ONE_ELEMENT)));
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, CustList.ONE_ELEMENT)));
            return;
        }
        if (realClassName_.startsWith(ARR)) {
            /*
            boolean       Z
            byte       B
            char       C
            class or interface       Lclassname;
            double       D
            float       F
            int       I
            long       J
            short       S
            */
            if (chidren_.isEmpty() && !elts_) {
                throw new EmptyArrayDimensionsException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
            if (!elts_) {
                for (OperationNode o: chidren_) {
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    if (!o.getResultClass().isNumericInt()) {
                        ClassArgumentMatching cl_ = o.getResultClass();
                        throw new BadIndexTypeException(cl_+RETURN_LINE+_conf.joinPages());
                    }
                }
            } else {
//                String eltType_ = PrimitiveTypeUtil.getComponentType(realClassName_);
                String eltType_ = PrimitiveTypeUtil.getQuickComponentType(realClassName_);
                for (OperationNode o: chidren_) {
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    String argType_ = o.getResultClass().getName();
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(eltType_, argType_, classes_)) {
                        throw new DynamicCastClassException(argType_+RETURN_LINE+eltType_+RETURN_LINE+_conf.joinPages());
                    }
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
////                if (StringList.quickEq(realClassName_,DOUBLE)) {
////                    realClassName_ = double.class.getName();
////                } else if (StringList.quickEq(realClassName_,FLOAT)) {
////                    realClassName_ = float.class.getName();
////                } else if (StringList.quickEq(realClassName_,LONG)) {
////                    realClassName_ = long.class.getName();
////                } else if (StringList.quickEq(realClassName_,INTEGER)) {
////                    realClassName_ = int.class.getName();
////                } else if (StringList.quickEq(realClassName_,SHORT)) {
////                    realClassName_ = short.class.getName();
////                } else if (StringList.quickEq(realClassName_,BYTE)) {
////                    realClassName_ = byte.class.getName();
////                } else if (StringList.quickEq(realClassName_,CHAR)) {
////                    realClassName_ = char.class.getName();
////                } else if (StringList.quickEq(realClassName_,BOOLEAN)) {
////                    realClassName_ = boolean.class.getName();
////                }
//                realClassName_ = PrimitiveTypeUtil.getAliasClass(realClassName_);
//            }
            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
            checkExist(_conf, realClassName_, true, false, 0);
            if (!elts_) {
//                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getArrayType(realClassName_, chidren_.size())));
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, chidren_.size())));
                return;
            }
//            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getArrayType(realClassName_, CustList.ONE_ELEMENT)));
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, CustList.ONE_ELEMENT)));
            return;
        }
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
//        boolean callIntern_ = false;
        realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
        if (isFirstChild() || !(getParent() instanceof DotOperation)) {
            boolean intern_ = false;
            if (StringList.isWord(realClassName_)) {
                setNeedPrevious(true);
                setResetablePreviousArg(true);
                ClassArgumentMatching arg_ = getPreviousResultClass();
                if (arg_ == null) {
                    throw new NullGlobalObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
                }
                //TODO wrap getDeclaredClasses
                for (Class<?> c:arg_.getDeclaredClasses()) {
                    if (StringList.quickEq(c.getSimpleName(), realClassName_)) {
                        intern_ = true;
                        firstArgs_.add(CustList.FIRST_INDEX, arg_);
                        realClassName_ = arg_.getName()+INTERN_CLASS+realClassName_;
                        break;
                    }
                }
            }
            constId = getDeclaredCustConstructor(_conf, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(firstArgs_));
            if (constId != null) {
                ClassMetaInfo custClass_ = null;
                custClass_ = classes_.getClassMetaInfo(realClassName_);
                String glClass_ = _conf.getLastPage().getGlobalClass();
                if (!classes_.canAccessClass(glClass_, realClassName_)) {
                    throw new BadAccessException(realClassName_+RETURN_LINE+_conf.joinPages());
                }
                if (!classes_.canAccessConstructor(glClass_, realClassName_, constId)) {
                    ConstructorBlock ctr_ = classes_.getConstructorBody(realClassName_, constId);
                    throw new BadAccessException(ctr_.getId().getSignature()+RETURN_LINE+_conf.joinPages());
                }
                if (custClass_.getCategory() == ClassCategory.ENUM) {
                    if (!_enumContext) {
                        throw new IllegalClassConstructorException(realClassName_+RETURN_LINE+_conf.joinPages());
                    }
                }
                possibleInitClass = true;
                setResultClass(new ClassArgumentMatching(realClassName_));
                return;
            }
            Class<?> cl_;
            try {
                if (StringList.quickEq(realClassName_, OperationNode.VOID_RETURN)) {
                    throw new VoidArgumentException(_conf.joinPages());
                }
                realClassName_ = PrimitiveTypeUtil.getArrayClass(realClassName_);
                cl_ = ConstClasses.classAliasForNameNotInit(realClassName_);
//                if (cl_ == void.class) {
//                    throw new VoidArgumentException(_conf.joinPages());
//                }
                if (cl_.isPrimitive()) {
                    throw new PrimitiveTypeException(realClassName_+RETURN_LINE+_conf.joinPages());
                }
            } catch (RuntimeClassNotFoundException _0_) {
                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
            if (cl_.isEnum()) {
                throw new IllegalClassConstructorException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
            if (isStaticAccess() && !Modifier.isStatic(cl_.getModifiers()) && intern_) {
                throw new StaticAccessException(_conf.joinPages());
            }
            Constructor<?> const_ = getDeclaredConstructor(_conf, 0, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(firstArgs_));
            if (!canBeUsed(const_, _conf)) {
                throw new BadAccessException(const_+RETURN_LINE+_conf.joinPages());
            }

            contructor = const_;
            setAccess(contructor, _conf);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        setNeedPrevious(true);
        setResetablePreviousArg(true);
        ClassArgumentMatching arg_ = getPreviousResultClass();
        if (arg_ == null) {
            throw new NullGlobalObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
        }
        firstArgs_.add(CustList.FIRST_INDEX, arg_);
        realClassName_ = arg_.getName()+INTERN_CLASS+realClassName_;
        constId = getDeclaredCustConstructor(_conf, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(firstArgs_));
        if (constId != null) {
            ClassMetaInfo custClass_ = null;
            custClass_ = classes_.getClassMetaInfo(realClassName_);
            String glClass_ = _conf.getLastPage().getGlobalClass();
            if (!classes_.canAccessClass(glClass_, realClassName_)) {
                throw new BadAccessException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
            if (!classes_.canAccessConstructor(glClass_, realClassName_, constId)) {
                ConstructorBlock ctr_ = classes_.getConstructorBody(realClassName_, constId);
                throw new BadAccessException(ctr_.getId().getSignature()+RETURN_LINE+_conf.joinPages());
            }
            if (custClass_.getCategory() == ClassCategory.ENUM) {
                if (!_enumContext) {
                    throw new IllegalClassConstructorException(realClassName_+RETURN_LINE+_conf.joinPages());
                }
            }
            possibleInitClass = true;
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        Class<?> cl_;
        try {
            if (StringList.quickEq(realClassName_, OperationNode.VOID_RETURN)) {
                throw new VoidArgumentException(_conf.joinPages());
            }
            realClassName_ = PrimitiveTypeUtil.getArrayClass(realClassName_);
            cl_ = ConstClasses.classAliasForNameNotInit(realClassName_);
//            if (cl_ == void.class) {
//                throw new VoidArgumentException(_conf.joinPages());
//            }
            if (cl_.isPrimitive()) {
                throw new PrimitiveTypeException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
        } catch (RuntimeClassNotFoundException _0_) {
            throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
        }
        if (cl_.isEnum()) {
            throw new IllegalClassConstructorException(cl_.getName()+RETURN_LINE+_conf.joinPages());
        }
        if (isStaticAccess() && !Modifier.isStatic(cl_.getModifiers())) {
            throw new StaticAccessException(_conf.joinPages());
        }
        Constructor<?> const_ = getDeclaredConstructor(_conf, 0, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(firstArgs_));
        if (!canBeUsed(const_, _conf)) {
            throw new BadAccessException(const_+RETURN_LINE+_conf.joinPages());
        }
        contructor = const_;
        setAccess(contructor, _conf);
        setResultClass(new ClassArgumentMatching(realClassName_));
    }

//    @Override
//    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
//            ContextEl _conf, Calculation _setting) {
//        CustList<OperationNode> chidren_ = getChildrenAmong();
//        int off_ = StringList.getFirstPrintableCharIndex(methodName);
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//        String className_ = methodName.trim().substring(INSTANCE.length()+2);
//        className_ = StringList.removeAllSpaces(className_);
//        boolean elts_ = false;
//        String realClassName_;
//        String instanceClassName_;
//        if (className_.endsWith(ARR_DYN)) {
//            elts_ = true;
//            int len_ = className_.length();
//            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
//        } else {
//            realClassName_ = className_;
//        }
//        if (realClassName_.startsWith(ARR) && realClassName_.endsWith(SUFFIX_INSTANCE)) {
//            int[] args_;
//            if (elts_) {
//                args_ = new int[CustList.ONE_ELEMENT];
//                args_[CustList.FIRST_INDEX] = chidren_.size();
//            } else {
//                args_ = new int[chidren_.size()];
//                int i_ = CustList.FIRST_INDEX;
//                for (OperationNode o: chidren_) {
//                    Number n_ = (Number)_nodes.getVal(o).getArgument().getObject();
//                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
//                    if (n_ == null) {
//                        throw new NullObjectException(i_+RETURN_LINE+_conf.joinPages());
//                    }
//                    int dim_ = n_.intValue();
//                    if (dim_ < 0) {
//                        throw new NegativeSizeException(String.valueOf(dim_)+RETURN_LINE+i_+RETURN_LINE+_conf.joinPages());
//                    }
//                    args_[i_] = dim_;
//                    i_++;
//                }
//            }
//            realClassName_ = realClassName_.substring(ARR.length());
//            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
//                realClassName_ = realClassName_.substring(ARR.length());
//                realClassName_ = StringList.removeStrings(realClassName_, SUFFIX_INSTANCE);
//            }
//            boolean cust_ = false;
//            Classes classes_ = _conf.getClasses();
//            ClassMetaInfo custClass_ = null;
//            instanceClassName_ = realClassName_;
//            if (classes_ != null) {
//                DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
//                custClass_ = classes_.getClassMetaInfo(clCurName_.getComponent());
//                if (custClass_ != null) {
//                    cust_ = true;
//                    int dim_ = clCurName_.getDim();
//                    instanceClassName_ = PrimitiveTypeUtil.getArrayType(Struct.class.getName(), dim_);
//                }
//            }
////            cust_ = true;
////            DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
////            int dim_ = clCurName_.getDim();
////            instanceClassName_ = PrimitiveTypeUtil.getArrayType(Struct.class.getName(), dim_);
//            Class<?> cl_;
//            try {
//                cl_ = ConstClasses.classForNameNotInit(instanceClassName_);
//            } catch (RuntimeClassNotFoundException _0_) {
//                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
//            }
////            Object o_ = Array.newInstance(cl_, args_);
//            Object o_ = Array.newInstance(cl_, args_);
//            Argument a_ = new Argument();
//            if (elts_) {
//                int i_ = CustList.FIRST_INDEX;
//                for (OperationNode o: chidren_) {
//                    Argument chArg_ = _nodes.getVal(o).getArgument();
//                    Struct str_ = chArg_.getStruct();
////                    Array.set(o_, i_, str_);
//                    if (cust_) {
//                        Array.set(o_, i_, str_);
//                    } else {
//                        Array.set(o_, i_, str_.getInstance());
//                    }
//                    i_++;
//                }
////            } else if (cust_ && o_ instanceof Struct[]) {
////                int len_ = chidren_.size();
////                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
////                    Array.set(o_, i, new Struct());
////                }
//            } else if (cust_) {
//                Numbers<Integer> dims_;
//                dims_ = new Numbers<Integer>();
//                for (int d: args_) {
//                    dims_.add(d);
//                }
//                for (Numbers<Integer> i: dims_.getAllIndexes()) {
//                    Object arr_ = o_;
//                    int s_ = i.size() - 1;
//                    for (int j = 0; j < s_; j++) {
//                        arr_ = Array.get(arr_, i.get(j));
//                    }
//                    Array.set(arr_, i.last(), new Struct());
//                }
//            }
////            a_.setObject(o_);
//            String clArr_ = PrimitiveTypeUtil.getArrayType(realClassName_, args_.length);
//            a_.setStructArgClassName(new Struct(o_,clArr_));
////            a_.setArgClassName(PrimitiveTypeUtil.getArrayType(realClassName_));
////            a_.setArgClassName(clArr_);
////            a_.setArgClassName(a_.getObjectClassName());
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        if (realClassName_.startsWith(ARR)) {
//            /*
//            boolean       Z
//            byte       B
//            char       C
//            class or interface       Lclassname;
//            double       D
//            float       F
//            int       I
//            long       J
//            short       S
//            */
//            int[] args_ = new int[chidren_.size()];
//            if (elts_) {
//                args_ = new int[CustList.ONE_ELEMENT];
//                args_[CustList.FIRST_INDEX] = chidren_.size();
//            } else {
//                args_ = new int[chidren_.size()];
//                int i_ = CustList.FIRST_INDEX;
//                for (OperationNode o: chidren_) {
//                    Number n_ = (Number)_nodes.getVal(o).getArgument().getObject();
//                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
//                    if (n_ == null) {
//                        throw new NullObjectException(i_+RETURN_LINE+_conf.joinPages());
//                    }
//                    int dim_ = n_.intValue();
//                    if (dim_ < 0) {
//                        throw new NegativeSizeException(String.valueOf(dim_)+RETURN_LINE+i_+RETURN_LINE+_conf.joinPages());
//                    }
//                    args_[i_] = dim_;
//                    i_++;
//                }
//            }
//            realClassName_ = realClassName_.substring(ARR.length());
//            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
////                if (StringList.quickEq(realClassName_,DOUBLE)) {
////                    realClassName_ = double.class.getName();
////                } else if (StringList.quickEq(realClassName_,FLOAT)) {
////                    realClassName_ = float.class.getName();
////                } else if (StringList.quickEq(realClassName_,LONG)) {
////                    realClassName_ = long.class.getName();
////                } else if (StringList.quickEq(realClassName_,INTEGER)) {
////                    realClassName_ = int.class.getName();
////                } else if (StringList.quickEq(realClassName_,SHORT)) {
////                    realClassName_ = short.class.getName();
////                } else if (StringList.quickEq(realClassName_,BYTE)) {
////                    realClassName_ = byte.class.getName();
////                } else if (StringList.quickEq(realClassName_,CHAR)) {
////                    realClassName_ = char.class.getName();
////                } else if (StringList.quickEq(realClassName_,BOOLEAN)) {
////                    realClassName_ = boolean.class.getName();
////                }
//                realClassName_ = PrimitiveTypeUtil.getAliasClass(realClassName_);
//            }
//            boolean cust_ = false;
//            Classes classes_ = _conf.getClasses();
//            ClassMetaInfo custClass_ = null;
//            instanceClassName_ = realClassName_;
//            if (classes_ != null) {
//                DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
//                custClass_ = classes_.getClassMetaInfo(clCurName_.getComponent());
//                if (custClass_ != null) {
//                    cust_ = true;
//                    int dim_ = clCurName_.getDim();
//                    instanceClassName_ = PrimitiveTypeUtil.getArrayType(Struct.class.getName(), dim_);
//                }
//            }
//            Class<?> cl_;
//            try {
//                cl_ = ConstClasses.classForNameNotInit(instanceClassName_);
//            } catch (RuntimeClassNotFoundException _0_) {
//                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
//            }
//            Object o_ = Array.newInstance(cl_, args_);
////            Object o_ = Array.newInstance(Struct.class, args_);
//            Argument a_ = new Argument();
//            if (elts_) {
//                int i_ = CustList.FIRST_INDEX;
//                for (OperationNode o: chidren_) {
//                    Argument chArg_ = _nodes.getVal(o).getArgument();
//                    Struct str_ = chArg_.getStruct();
//                    if (cust_) {
//                        Array.set(o_, i_, str_);
//                    } else {
//                        Array.set(o_, i_, str_.getInstance());
//                    }
//                    i_++;
//                }
//            } else if (cust_ && o_ instanceof Struct[]) {
//                int len_ = chidren_.size();
//                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//                    Array.set(o_, i, new Struct());
//                }
//            }
//            a_.setObject(o_);
////            a_.setArgClassName(o_.getClass().getName());
////            a_.setArgClassName(a_.getObjectClassName());
//            a_.setArgClassName(PrimitiveTypeUtil.getArrayType(realClassName_, args_.length));
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        if (possibleInitClass) {
//            if (!_conf.getClasses().isInitialized(realClassName_)) {
//                _conf.getClasses().initialize(realClassName_);
//                throw new NotInitializedClassException(realClassName_);
//            }
//        }
//        CustList<Argument> firstArgs_ = listArguments(chidren_, _nodes, true);
//        if (isFirstChild() || !(getParent() instanceof DotOperation)) {
//            Argument needed_ = null;
//            if (StringList.isWord(realClassName_)) {
//                for (Class<?> c:getPreviousResultClass().getDeclaredClasses()) {
//                    if (StringList.quickEq(c.getSimpleName(), realClassName_)) {
//                        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
//                        if (arg_.isNull()) {
//                            throw new NullObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
//                        }
//                        needed_ = arg_;
//                        firstArgs_.add(CustList.FIRST_INDEX, arg_);
//                        break;
//                    }
//                }
//            }
//            if (constId != null) {
//                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
//                throw new CustomFoundConstructorException(realClassName_, called_, constId, needed_, firstArgs_, InstancingStep.NEWING);
//            }
//            Argument a_ = newInstance(_conf, needed_, 0, contructor, Argument.toArgArray(firstArgs_));
//            setSimpleArgument(a_, _conf, _nodes);
//            return a_;
//        }
//        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
//        if (arg_.isNull()) {
//            throw new NullObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
//        }
//        firstArgs_.add(CustList.FIRST_INDEX, arg_);
//        if (constId != null) {
//            StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
//            throw new CustomFoundConstructorException(realClassName_, called_, constId, arg_, firstArgs_, InstancingStep.NEWING);
//        }
//        Argument a_ = newInstance(_conf, arg_, 0, contructor, Argument.toArgArray(firstArgs_));
//        setSimpleArgument(a_, _conf, _nodes);
//        return a_;
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
        CustList<OperationNode> chidren_ = getChildrenAmong();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = methodName.trim().substring(INSTANCE.length()+2);
        className_ = StringList.removeAllSpaces(className_);
        boolean elts_ = false;
        String realClassName_;
        String instanceClassName_;
        if (className_.endsWith(ARR_DYN)) {
            elts_ = true;
            int len_ = className_.length();
            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
        } else {
            realClassName_ = className_;
        }
        if (realClassName_.startsWith(ARR) && realClassName_.contains(DOT)) {
            int[] args_;
            if (elts_) {
                args_ = new int[CustList.ONE_ELEMENT];
                args_[CustList.FIRST_INDEX] = chidren_.size();
            } else {
                args_ = new int[chidren_.size()];
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Number n_ = (Number)_nodes.getVal(o).getArgument().getObject();
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    if (n_ == null) {
                        throw new NullObjectException(i_+RETURN_LINE+_conf.joinPages());
                    }
                    int dim_ = n_.intValue();
                    if (dim_ < 0) {
                        throw new NegativeSizeException(String.valueOf(dim_)+RETURN_LINE+i_+RETURN_LINE+_conf.joinPages());
                    }
                    args_[i_] = dim_;
                    i_++;
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
//                realClassName_ = realClassName_.substring(ARR.length());
//                realClassName_ = StringList.removeStrings(realClassName_, SUFFIX_INSTANCE);
//            }
            boolean cust_ = false;
            Classes classes_ = _conf.getClasses();
            ClassMetaInfo custClass_ = null;
            instanceClassName_ = realClassName_;
            if (classes_ != null) {
//                DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
                DimComp clCurName_ = PrimitiveTypeUtil.getQuickComponentBaseType(realClassName_);
                custClass_ = classes_.getClassMetaInfo(clCurName_.getComponent());
                if (custClass_ != null) {
                    cust_ = true;
                    int dim_ = clCurName_.getDim();
                    instanceClassName_ = PrimitiveTypeUtil.getPrettyArrayType(Struct.class.getName(), dim_);
                }
            }
//            cust_ = true;
//            DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
//            int dim_ = clCurName_.getDim();
//            instanceClassName_ = PrimitiveTypeUtil.getArrayType(Struct.class.getName(), dim_);
            Class<?> cl_;
            try {
                cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(instanceClassName_));
            } catch (RuntimeClassNotFoundException _0_) {
                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
//            Object o_ = Array.newInstance(cl_, args_);
            Object o_ = Array.newInstance(cl_, args_);
            Argument a_ = new Argument();
            if (elts_) {
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Argument chArg_ = _nodes.getVal(o).getArgument();
                    Struct str_ = chArg_.getStruct();
//                    Array.set(o_, i_, str_);
                    if (cust_) {
                        Array.set(o_, i_, str_);
                    } else {
                        Array.set(o_, i_, str_.getInstance());
                    }
                    i_++;
                }
//            } else if (cust_ && o_ instanceof Struct[]) {
//                int len_ = chidren_.size();
//                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//                    Array.set(o_, i, new Struct());
//                }
            } else if (cust_) {
                Numbers<Integer> dims_;
                dims_ = new Numbers<Integer>();
                for (int d: args_) {
                    dims_.add(d);
                }
                for (Numbers<Integer> i: dims_.getAllIndexes()) {
                    Object arr_ = o_;
                    int s_ = i.size() - 1;
                    for (int j = 0; j < s_; j++) {
                        arr_ = Array.get(arr_, i.get(j));
                    }
                    Array.set(arr_, i.last(), new Struct());
                }
            }
//            a_.setObject(o_);
//            String clArr_ = PrimitiveTypeUtil.getArrayType(realClassName_, args_.length);
            String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(realClassName_, args_.length);
            a_.setStructArgClassName(new Struct(o_,clArr_));
//            a_.setArgClassName(PrimitiveTypeUtil.getArrayType(realClassName_));
//            a_.setArgClassName(clArr_);
//            a_.setArgClassName(a_.getObjectClassName());
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (realClassName_.startsWith(ARR)) {
            /*
            boolean       Z
            byte       B
            char       C
            class or interface       Lclassname;
            double       D
            float       F
            int       I
            long       J
            short       S
            */
            int[] args_ = new int[chidren_.size()];
            if (elts_) {
                args_ = new int[CustList.ONE_ELEMENT];
                args_[CustList.FIRST_INDEX] = chidren_.size();
            } else {
                args_ = new int[chidren_.size()];
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Number n_ = (Number)_nodes.getVal(o).getArgument().getObject();
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    if (n_ == null) {
                        throw new NullObjectException(i_+RETURN_LINE+_conf.joinPages());
                    }
                    int dim_ = n_.intValue();
                    if (dim_ < 0) {
                        throw new NegativeSizeException(String.valueOf(dim_)+RETURN_LINE+i_+RETURN_LINE+_conf.joinPages());
                    }
                    args_[i_] = dim_;
                    i_++;
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
////                if (StringList.quickEq(realClassName_,DOUBLE)) {
////                    realClassName_ = double.class.getName();
////                } else if (StringList.quickEq(realClassName_,FLOAT)) {
////                    realClassName_ = float.class.getName();
////                } else if (StringList.quickEq(realClassName_,LONG)) {
////                    realClassName_ = long.class.getName();
////                } else if (StringList.quickEq(realClassName_,INTEGER)) {
////                    realClassName_ = int.class.getName();
////                } else if (StringList.quickEq(realClassName_,SHORT)) {
////                    realClassName_ = short.class.getName();
////                } else if (StringList.quickEq(realClassName_,BYTE)) {
////                    realClassName_ = byte.class.getName();
////                } else if (StringList.quickEq(realClassName_,CHAR)) {
////                    realClassName_ = char.class.getName();
////                } else if (StringList.quickEq(realClassName_,BOOLEAN)) {
////                    realClassName_ = boolean.class.getName();
////                }
//                realClassName_ = PrimitiveTypeUtil.getAliasClass(realClassName_);
//            }
            boolean cust_ = false;
            Classes classes_ = _conf.getClasses();
            ClassMetaInfo custClass_ = null;
            instanceClassName_ = realClassName_;
            if (classes_ != null) {
//                DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
                DimComp clCurName_ = PrimitiveTypeUtil.getQuickComponentBaseType(realClassName_);
                custClass_ = classes_.getClassMetaInfo(clCurName_.getComponent());
                if (custClass_ != null) {
                    cust_ = true;
                    int dim_ = clCurName_.getDim();
                    instanceClassName_ = PrimitiveTypeUtil.getPrettyArrayType(Struct.class.getName(), dim_);
                }
            }
            Class<?> cl_;
            try {
                if (instanceClassName_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    cl_ = ConstClasses.getPrimitiveClass(instanceClassName_.substring(1));
                } else {
                    cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(instanceClassName_));
                }
            } catch (RuntimeClassNotFoundException _0_) {
                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
            Object o_ = Array.newInstance(cl_, args_);
//            Object o_ = Array.newInstance(Struct.class, args_);
            Argument a_ = new Argument();
            if (elts_) {
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Argument chArg_ = _nodes.getVal(o).getArgument();
                    Struct str_ = chArg_.getStruct();
                    if (cust_) {
                        Array.set(o_, i_, str_);
                    } else {
                        Array.set(o_, i_, str_.getInstance());
                    }
                    i_++;
                }
            } else if (cust_ && o_ instanceof Struct[]) {
                int len_ = chidren_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Array.set(o_, i, new Struct());
                }
            }
//            a_.setObject(o_);
            a_.setStruct(new Struct(o_,PrimitiveTypeUtil.getPrettyArrayType(realClassName_, args_.length)));
//            a_.setArgClassName(o_.getClass().getName());
//            a_.setArgClassName(a_.getObjectClassName());
//            a_.setArgClassName(PrimitiveTypeUtil.getArrayType(realClassName_, args_.length));
            a_.setArgClassName(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, args_.length));
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        if (possibleInitClass) {
            if (!_conf.getClasses().isInitialized(realClassName_)) {
                _conf.getClasses().initialize(realClassName_);
                throw new NotInitializedClassException(realClassName_);
            }
        }
        CustList<Argument> firstArgs_ = listArguments(chidren_, _nodes, true);
        if (isFirstChild() || !(getParent() instanceof DotOperation)) {
            Argument needed_ = null;
            if (StringList.isWord(realClassName_)) {
                for (Class<?> c:getPreviousResultClass().getDeclaredClasses()) {
                    if (StringList.quickEq(c.getSimpleName(), realClassName_)) {
                        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
                        if (arg_.isNull()) {
                            throw new NullObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
                        }
                        needed_ = arg_;
                        firstArgs_.add(CustList.FIRST_INDEX, arg_);
                        break;
                    }
                }
            }
            if (constId != null) {
                StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
                throw new CustomFoundConstructorException(realClassName_, called_, constId, needed_, firstArgs_, InstancingStep.NEWING);
            }
            Argument a_ = newInstance(_conf, needed_, 0, contructor, Argument.toArgArray(firstArgs_));
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Argument arg_ = _nodes.getVal(this).getPreviousArgument();
        if (arg_.isNull()) {
            throw new NullObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
        }
        firstArgs_.add(CustList.FIRST_INDEX, arg_);
        if (constId != null) {
            StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
            throw new CustomFoundConstructorException(realClassName_, called_, constId, arg_, firstArgs_, InstancingStep.NEWING);
        }
        Argument a_ = newInstance(_conf, arg_, 0, contructor, Argument.toArgArray(firstArgs_));
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws NegativeSizeException
    @throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException*/
//    @Override
//    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting) {
//        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
//        int off_ = StringList.getFirstPrintableCharIndex(methodName);
//        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
//        String className_ = methodName.trim().substring(INSTANCE.length()+2);
//        className_ = StringList.removeAllSpaces(className_);
//        boolean elts_ = false;
//        String realClassName_;
//        String instanceClassName_;
//        if (className_.endsWith(ARR_DYN)) {
//            elts_ = true;
//            int len_ = className_.length();
//            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
//        } else {
//            realClassName_ = className_;
//        }
//        if (realClassName_.startsWith(ARR) && realClassName_.endsWith(SUFFIX_INSTANCE)) {
//            int[] args_;
//            if (elts_) {
//                args_ = new int[CustList.ONE_ELEMENT];
//                args_[CustList.FIRST_INDEX] = chidren_.size();
//            } else {
//                args_ = new int[chidren_.size()];
//                int i_ = CustList.FIRST_INDEX;
//                for (OperationNode o: chidren_) {
//                    Number n_ = (Number)o.getArgument().getObject();
//                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
//                    if (n_ == null) {
//                        throw new NullObjectException(i_+RETURN_LINE+_conf.joinPages());
//                    }
//                    int dim_ = n_.intValue();
//                    if (dim_ < 0) {
//                        throw new NegativeSizeException(String.valueOf(dim_)+RETURN_LINE+i_+RETURN_LINE+_conf.joinPages());
//                    }
//                    args_[i_] = dim_;
//                    i_++;
//                }
//            }
//            realClassName_ = realClassName_.substring(ARR.length());
//            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
//                realClassName_ = realClassName_.substring(ARR.length());
//                realClassName_ = StringList.removeStrings(realClassName_, SUFFIX_INSTANCE);
//            }
//            boolean cust_ = false;
//            Classes classes_ = _conf.getClasses();
//            ClassMetaInfo custClass_ = null;
//            instanceClassName_ = realClassName_;
//            if (classes_ != null) {
//                DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
//                custClass_ = classes_.getClassMetaInfo(clCurName_.getComponent());
//                if (custClass_ != null) {
//                    cust_ = true;
//                    int dim_ = clCurName_.getDim();
//                    instanceClassName_ = PrimitiveTypeUtil.getArrayType(Struct.class.getName(), dim_);
//                }
//            }
////            DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
////            int dim_ = clCurName_.getDim();
////            instanceClassName_ = PrimitiveTypeUtil.getArrayType(Struct.class.getName(), dim_);
//            Class<?> cl_;
//            try {
//                cl_ = ConstClasses.classForNameNotInit(instanceClassName_);
//            } catch (RuntimeClassNotFoundException _0_) {
//                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
//            }
////            Object[][] objs_ = new Object[][]{new Object[]{}};
////            int[][] a_ = new int[3][];//[5];
//            Object o_ = Array.newInstance(cl_, args_);
////            Object o_ = Array.newInstance(Struct.class, args_);
////            Object o_ = Array.newInstance(Object.class, args_);
//            String type_ = PrimitiveTypeUtil.getArrayType(realClassName_, args_.length);
////            Struct struct_ = new Struct(o_,type_);
////            Object o_ = Array.newInstance(Object.class, args_);
//            Argument a_ = new Argument();
//            if (elts_) {
//                int i_ = CustList.FIRST_INDEX;
//                for (OperationNode o: chidren_) {
//                    Argument chArg_ = o.getArgument();
//                    Struct str_ = chArg_.getStruct();
////                    Array.set(o_, i_, str_);
//                    if (cust_) {
//                        Array.set(o_, i_, str_);
//                    } else {
//                        Array.set(o_, i_, str_.getInstance());
//                    }
//                    i_++;
//                }
////            } else if (o_ instanceof Struct[]) {
////                int len_ = chidren_.size();
////                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
////                    Array.set(o_, i, new Struct());
////                }
//            } else if (cust_) {
//                Numbers<Integer> dims_;
//                dims_ = new Numbers<Integer>();
//                for (int d: args_) {
//                    dims_.add(d);
//                }
//                for (Numbers<Integer> i: dims_.getAllIndexes()) {
//                    Object arr_ = o_;
//                    int s_ = i.size() - 1;
//                    for (int j = 0; j < s_; j++) {
//                        arr_ = Array.get(arr_, i.get(j));
//                    }
//                    Array.set(arr_, i.last(), new Struct());
//                }
//            }
////            String clArr_ = PrimitiveTypeUtil.getArrayType(realClassName_);
//            a_.setStructArgClassName(new Struct(o_,type_));
////            a_.setObject(o_);
////            a_.setStruct(struct_);
////            a_.setArgClassName(o_.getClass().getName());
////            a_.setArgClassName(a_.getObjectClassName());
////            a_.setArgClassName(type_);
//            setSimpleArgument(a_, _conf);
//            return;
//        }
//        if (realClassName_.startsWith(ARR)) {
//            /*
//            boolean       Z
//            byte       B
//            char       C
//            class or interface       Lclassname;
//            double       D
//            float       F
//            int       I
//            long       J
//            short       S
//            */
//            int[] args_;
//            if (elts_) {
//                args_ = new int[CustList.ONE_ELEMENT];
//                args_[CustList.FIRST_INDEX] = chidren_.size();
//            } else {
//                args_ = new int[chidren_.size()];
//                int i_ = CustList.FIRST_INDEX;
//                for (OperationNode o: chidren_) {
//                    Number n_ = (Number)o.getArgument().getObject();
//                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
//                    if (n_ == null) {
//                        throw new NullObjectException(i_+RETURN_LINE+_conf.joinPages());
//                    }
//                    int dim_ = n_.intValue();
//                    if (dim_ < 0) {
//                        throw new NegativeSizeException(String.valueOf(dim_)+RETURN_LINE+i_+RETURN_LINE+_conf.joinPages());
//                    }
//                    args_[i_] = dim_;
//                    i_++;
//                }
//            }
//            realClassName_ = realClassName_.substring(ARR.length());
//            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
////                if (StringList.quickEq(realClassName_,DOUBLE)) {
////                    realClassName_ = double.class.getName();
////                } else if (StringList.quickEq(realClassName_,FLOAT)) {
////                    realClassName_ = float.class.getName();
////                } else if (StringList.quickEq(realClassName_,LONG)) {
////                    realClassName_ = long.class.getName();
////                } else if (StringList.quickEq(realClassName_,INTEGER)) {
////                    realClassName_ = int.class.getName();
////                } else if (StringList.quickEq(realClassName_,SHORT)) {
////                    realClassName_ = short.class.getName();
////                } else if (StringList.quickEq(realClassName_,BYTE)) {
////                    realClassName_ = byte.class.getName();
////                } else if (StringList.quickEq(realClassName_,CHAR)) {
////                    realClassName_ = char.class.getName();
////                } else if (StringList.quickEq(realClassName_,BOOLEAN)) {
////                    realClassName_ = boolean.class.getName();
////                }
//                realClassName_ = PrimitiveTypeUtil.getAliasClass(realClassName_);
//            }
//            boolean cust_ = false;
//            Classes classes_ = _conf.getClasses();
//            ClassMetaInfo custClass_ = null;
//            instanceClassName_ = realClassName_;
//            if (classes_ != null) {
//                DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
//                custClass_ = classes_.getClassMetaInfo(clCurName_.getComponent());
//                if (custClass_ != null) {
//                    cust_ = true;
//                    int dim_ = clCurName_.getDim();
//                    instanceClassName_ = PrimitiveTypeUtil.getArrayType(Struct.class.getName(), dim_);
//                }
//            }
//            Class<?> cl_;
//            try {
//                cl_ = ConstClasses.classForNameNotInit(instanceClassName_);
//            } catch (RuntimeClassNotFoundException _0_) {
//                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
//            }
//            Object o_ = Array.newInstance(cl_, args_);
////            Object o_ = Array.newInstance(Struct.class, args_);
//            Argument a_ = new Argument();
//            if (elts_) {
//                int i_ = CustList.FIRST_INDEX;
//                for (OperationNode o: chidren_) {
//                    Argument chArg_ = o.getArgument();
//                    Struct str_ = chArg_.getStruct();
//                    if (cust_) {
//                        Array.set(o_, i_, str_);
//                    } else {
//                        Array.set(o_, i_, str_.getInstance());
//                    }
//                    i_++;
//                }
//            } else if (cust_ && o_ instanceof Struct[]) {
//                int len_ = chidren_.size();
//                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//                    Array.set(o_, i, new Struct());
//                }
//            }
//            a_.setObject(o_);
////            a_.setArgClassName(o_.getClass().getName());
////            a_.setArgClassName(a_.getObjectClassName());
//            String type_ = PrimitiveTypeUtil.getArrayType(realClassName_, args_.length);
//            a_.setArgClassName(type_);
//            setSimpleArgument(a_, _conf);
//            return;
//        }
//        if (possibleInitClass) {
//            if (!_conf.getClasses().isInitialized(realClassName_)) {
//                _conf.getClasses().initialize(realClassName_);
//                ProcessXmlMethod.initializeClass(realClassName_, _conf);
//            }
//        }
//        CustList<Argument> firstArgs_ = listArguments(chidren_, true);
//        if (isFirstChild() || !(getParent() instanceof DotOperation)) {
////            Argument[] args_ = new Argument[chidren_.size()];
////            int i_ = CustList.FIRST_INDEX;
////            for (OperationNode o: chidren_) {
////                args_[i_] = o.getArgument();
////                i_++;
////            }
////            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
////            Class<?> cl_ = ConstClasses.classForName(realClassName_);
//            //Argument a_ = getConstructorThenInvoke(_conf, 0, cl_, firstArgs_.toArray(new Argument[0]));
//            Argument needed_ = null;
//            if (StringList.isWord(realClassName_)) {
//                for (Class<?> c:getPreviousResultClass().getDeclaredClasses()) {
//                    if (StringList.quickEq(c.getSimpleName(), realClassName_)) {
//                        Argument arg_ = getPreviousArgument();
//                        if (arg_.isNull()) {
//                            throw new NullObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
//                        }
//                        needed_ = arg_;
//                        firstArgs_.add(CustList.FIRST_INDEX, arg_);
//                        break;
//                    }
//                }
//            }
//            if (constId != null) {
//                Argument a_ = ProcessXmlMethod.instanceArgument(realClassName_, needed_, constId, firstArgs_, _conf);
//                setSimpleArgument(a_, _conf);
//                return;
//            }
//            Argument a_ = newInstance(_conf, needed_, 0, contructor, Argument.toArgArray(firstArgs_));
//            setSimpleArgument(a_, _conf);
//            return;
//        }
////        Class<?> cl_ = arg_.getArgClass();
////        Object obj_ = arg_.getObject();
//        Argument arg_ = getPreviousArgument();
//        if (arg_.isNull()) {
//            throw new NullObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
//        }
//        firstArgs_.add(CustList.FIRST_INDEX, arg_);
////        Argument[] args_ = new Argument[chidren_.size()+1];
////        args_[CustList.FIRST_INDEX] = arg_;
////        int i_ = CustList.SECOND_INDEX;
////        for (OperationNode o: chidren_) {
////            args_[i_] = o.getArgument();
////            i_++;
////        }
////        realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
////        realClassName_ = arg_.getArgClass().getName()+INTERN_CLASS+realClassName_;
////        Class<?> cl_ = ConstClasses.classForName(realClassName_);
//        if (constId != null) {
//            Argument a_ = ProcessXmlMethod.instanceArgument(realClassName_, arg_, constId, firstArgs_, _conf);
//            setSimpleArgument(a_, _conf);
//            return;
//        }
//        Argument a_ = newInstance(_conf, arg_, 0, contructor, Argument.toArgArray(firstArgs_));
////        setArgument(getConstructorThenInvoke(_conf, 0, cl_, firstArgs_.toArray(new Argument[0])));
//        setSimpleArgument(a_, _conf);
////        setArgument(getMethodThenInvoke(_conf, 0, obj_, cl_, methodName, args_));
////        SerializeXmlObject.getDeclaredMethod(cl_, methodName, _argsClass);
//    }

//    @Override
//    public void setPreviousArgument(Argument _previousArgument) {
////        if (isFirstLeaf() && !needPrevious) {
////            setCalulated(true);
////            return;
////        }
//        if (!isNeedPrevious()) {
////            setCalulated(true);
//            return;
//        }
//        super.setPreviousArgument(_previousArgument);
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
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = methodName.trim().substring(INSTANCE.length()+2);
        className_ = StringList.removeAllSpaces(className_);
        boolean elts_ = false;
        String realClassName_;
        String instanceClassName_;
        if (className_.endsWith(ARR_DYN)) {
            elts_ = true;
            int len_ = className_.length();
            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
        } else {
            realClassName_ = className_;
        }
        if (realClassName_.startsWith(ARR) && realClassName_.contains(DOT)) {
            int[] args_;
            if (elts_) {
                args_ = new int[CustList.ONE_ELEMENT];
                args_[CustList.FIRST_INDEX] = chidren_.size();
            } else {
                args_ = new int[chidren_.size()];
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Number n_ = (Number)o.getArgument().getObject();
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    if (n_ == null) {
                        throw new NullObjectException(i_+RETURN_LINE+_conf.joinPages());
                    }
                    int dim_ = n_.intValue();
                    if (dim_ < 0) {
                        throw new NegativeSizeException(String.valueOf(dim_)+RETURN_LINE+i_+RETURN_LINE+_conf.joinPages());
                    }
                    args_[i_] = dim_;
                    i_++;
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
//                realClassName_ = realClassName_.substring(ARR.length());
//                realClassName_ = StringList.removeStrings(realClassName_, SUFFIX_INSTANCE);
//            }
            boolean cust_ = false;
            Classes classes_ = _conf.getClasses();
            ClassMetaInfo custClass_ = null;
            instanceClassName_ = realClassName_;
            if (classes_ != null) {
//                DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
                DimComp clCurName_ = PrimitiveTypeUtil.getQuickComponentBaseType(realClassName_);
                custClass_ = classes_.getClassMetaInfo(clCurName_.getComponent());
                if (custClass_ != null) {
                    cust_ = true;
                    int dim_ = clCurName_.getDim();
                    instanceClassName_ = PrimitiveTypeUtil.getPrettyArrayType(Struct.class.getName(), dim_);
                }
            }
//            DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
//            int dim_ = clCurName_.getDim();
//            instanceClassName_ = PrimitiveTypeUtil.getArrayType(Struct.class.getName(), dim_);
            Class<?> cl_;
            try {
                cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(instanceClassName_));
            } catch (RuntimeClassNotFoundException _0_) {
                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
//            Object[][] objs_ = new Object[][]{new Object[]{}};
//            int[][] a_ = new int[3][];//[5];
            Object o_ = Array.newInstance(cl_, args_);
//            Object o_ = Array.newInstance(Struct.class, args_);
//            Object o_ = Array.newInstance(Object.class, args_);
//            String type_ = PrimitiveTypeUtil.getArrayType(realClassName_, args_.length);
            String type_ = PrimitiveTypeUtil.getPrettyArrayType(realClassName_, args_.length);
//            Struct struct_ = new Struct(o_,type_);
//            Object o_ = Array.newInstance(Object.class, args_);
            Argument a_ = new Argument();
            if (elts_) {
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Argument chArg_ = o.getArgument();
                    Struct str_ = chArg_.getStruct();
//                    Array.set(o_, i_, str_);
                    if (cust_) {
                        Array.set(o_, i_, str_);
                    } else {
                        Array.set(o_, i_, str_.getInstance());
                    }
                    i_++;
                }
//            } else if (o_ instanceof Struct[]) {
//                int len_ = chidren_.size();
//                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//                    Array.set(o_, i, new Struct());
//                }
            } else if (cust_) {
                Numbers<Integer> dims_;
                dims_ = new Numbers<Integer>();
                for (int d: args_) {
                    dims_.add(d);
                }
                for (Numbers<Integer> i: dims_.getAllIndexes()) {
                    Object arr_ = o_;
                    int s_ = i.size() - 1;
                    for (int j = 0; j < s_; j++) {
                        arr_ = Array.get(arr_, i.get(j));
                    }
                    Array.set(arr_, i.last(), new Struct());
                }
            }
//            String clArr_ = PrimitiveTypeUtil.getArrayType(realClassName_);
            a_.setStructArgClassName(new Struct(o_,type_));
//            a_.setObject(o_);
//            a_.setStruct(struct_);
//            a_.setArgClassName(o_.getClass().getName());
//            a_.setArgClassName(a_.getObjectClassName());
//            a_.setArgClassName(type_);
            setSimpleArgument(a_, _conf);
            return;
        }
        if (realClassName_.startsWith(ARR)) {
            /*
            boolean       Z
            byte       B
            char       C
            class or interface       Lclassname;
            double       D
            float       F
            int       I
            long       J
            short       S
            */
            int[] args_;
            if (elts_) {
                args_ = new int[CustList.ONE_ELEMENT];
                args_[CustList.FIRST_INDEX] = chidren_.size();
            } else {
                args_ = new int[chidren_.size()];
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Number n_ = (Number)o.getArgument().getObject();
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    if (n_ == null) {
                        throw new NullObjectException(i_+RETURN_LINE+_conf.joinPages());
                    }
                    int dim_ = n_.intValue();
                    if (dim_ < 0) {
                        throw new NegativeSizeException(String.valueOf(dim_)+RETURN_LINE+i_+RETURN_LINE+_conf.joinPages());
                    }
                    args_[i_] = dim_;
                    i_++;
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            if (!realClassName_.startsWith(ARR)) {
////                if (StringList.quickEq(realClassName_,DOUBLE)) {
////                    realClassName_ = double.class.getName();
////                } else if (StringList.quickEq(realClassName_,FLOAT)) {
////                    realClassName_ = float.class.getName();
////                } else if (StringList.quickEq(realClassName_,LONG)) {
////                    realClassName_ = long.class.getName();
////                } else if (StringList.quickEq(realClassName_,INTEGER)) {
////                    realClassName_ = int.class.getName();
////                } else if (StringList.quickEq(realClassName_,SHORT)) {
////                    realClassName_ = short.class.getName();
////                } else if (StringList.quickEq(realClassName_,BYTE)) {
////                    realClassName_ = byte.class.getName();
////                } else if (StringList.quickEq(realClassName_,CHAR)) {
////                    realClassName_ = char.class.getName();
////                } else if (StringList.quickEq(realClassName_,BOOLEAN)) {
////                    realClassName_ = boolean.class.getName();
////                }
//                realClassName_ = PrimitiveTypeUtil.getAliasClass(realClassName_);
//            }
            boolean cust_ = false;
            Classes classes_ = _conf.getClasses();
            ClassMetaInfo custClass_ = null;
            instanceClassName_ = realClassName_;
            if (classes_ != null) {
//                DimComp clCurName_ = PrimitiveTypeUtil.getComponentBaseType(realClassName_);
                DimComp clCurName_ = PrimitiveTypeUtil.getQuickComponentBaseType(realClassName_);
                custClass_ = classes_.getClassMetaInfo(clCurName_.getComponent());
                if (custClass_ != null) {
                    cust_ = true;
                    int dim_ = clCurName_.getDim();
                    instanceClassName_ = PrimitiveTypeUtil.getPrettyArrayType(Struct.class.getName(), dim_);
                }
            }
            Class<?> cl_;
            try {
                if (instanceClassName_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    cl_ = ConstClasses.getPrimitiveClass(instanceClassName_.substring(1));
                } else {
                    cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(instanceClassName_));
                }
            } catch (RuntimeClassNotFoundException _0_) {
                throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
            Object o_ = Array.newInstance(cl_, args_);
//            Object o_ = Array.newInstance(Struct.class, args_);
            Argument a_ = new Argument();
            if (elts_) {
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Argument chArg_ = o.getArgument();
                    Struct str_ = chArg_.getStruct();
                    if (cust_) {
                        Array.set(o_, i_, str_);
                    } else {
                        Array.set(o_, i_, str_.getInstance());
                    }
                    i_++;
                }
            } else if (cust_ && o_ instanceof Struct[]) {
                int len_ = chidren_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Array.set(o_, i, new Struct());
                }
            }
//            a_.setObject(o_);
            a_.setStruct(new Struct(o_,PrimitiveTypeUtil.getPrettyArrayType(realClassName_, args_.length)));
//            a_.setArgClassName(o_.getClass().getName());
//            a_.setArgClassName(a_.getObjectClassName());
//            String type_ = PrimitiveTypeUtil.getArrayType(realClassName_, args_.length);
            String type_ = PrimitiveTypeUtil.getPrettyArrayType(realClassName_, args_.length);
            a_.setArgClassName(type_);
            setSimpleArgument(a_, _conf);
            return;
        }
        if (possibleInitClass) {
            if (!_conf.getClasses().isInitialized(realClassName_)) {
                _conf.getClasses().initialize(realClassName_);
                ProcessXmlMethod.initializeClass(realClassName_, _conf);
            }
        }
        CustList<Argument> firstArgs_ = listArguments(chidren_, true);
        if (isFirstChild() || !(getParent() instanceof DotOperation)) {
//            Argument[] args_ = new Argument[chidren_.size()];
//            int i_ = CustList.FIRST_INDEX;
//            for (OperationNode o: chidren_) {
//                args_[i_] = o.getArgument();
//                i_++;
//            }
//            realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//            Class<?> cl_ = ConstClasses.classForName(realClassName_);
            //Argument a_ = getConstructorThenInvoke(_conf, 0, cl_, firstArgs_.toArray(new Argument[0]));
            Argument needed_ = null;
            if (StringList.isWord(realClassName_)) {
                for (Class<?> c:getPreviousResultClass().getDeclaredClasses()) {
                    if (StringList.quickEq(c.getSimpleName(), realClassName_)) {
                        Argument arg_ = getPreviousArgument();
                        if (arg_.isNull()) {
                            throw new NullObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
                        }
                        needed_ = arg_;
                        firstArgs_.add(CustList.FIRST_INDEX, arg_);
                        break;
                    }
                }
            }
            if (constId != null) {
                Argument a_ = ProcessXmlMethod.instanceArgument(realClassName_, needed_, constId, firstArgs_, _conf);
                setSimpleArgument(a_, _conf);
                return;
            }
            Argument a_ = newInstance(_conf, needed_, 0, contructor, Argument.toArgArray(firstArgs_));
            setSimpleArgument(a_, _conf);
            return;
        }
//        Class<?> cl_ = arg_.getArgClass();
//        Object obj_ = arg_.getObject();
        Argument arg_ = getPreviousArgument();
        if (arg_.isNull()) {
            throw new NullObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
        }
        firstArgs_.add(CustList.FIRST_INDEX, arg_);
//        Argument[] args_ = new Argument[chidren_.size()+1];
//        args_[CustList.FIRST_INDEX] = arg_;
//        int i_ = CustList.SECOND_INDEX;
//        for (OperationNode o: chidren_) {
//            args_[i_] = o.getArgument();
//            i_++;
//        }
//        realClassName_ = realClassName_.replace(EXTERN_CLASS, DOT_VAR);
//        realClassName_ = arg_.getArgClass().getName()+INTERN_CLASS+realClassName_;
//        Class<?> cl_ = ConstClasses.classForName(realClassName_);
        if (constId != null) {
            Argument a_ = ProcessXmlMethod.instanceArgument(realClassName_, arg_, constId, firstArgs_, _conf);
            setSimpleArgument(a_, _conf);
            return;
        }
        Argument a_ = newInstance(_conf, arg_, 0, contructor, Argument.toArgArray(firstArgs_));
//        setArgument(getConstructorThenInvoke(_conf, 0, cl_, firstArgs_.toArray(new Argument[0])));
        setSimpleArgument(a_, _conf);
//        setArgument(getMethodThenInvoke(_conf, 0, obj_, cl_, methodName, args_));
//        SerializeXmlObject.getDeclaredMethod(cl_, methodName, _argsClass);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    boolean isCallMethodCtor() {
        String className_ = methodName.trim().substring(INSTANCE.length()+2);
        className_ = StringList.removeAllSpaces(className_);
        String realClassName_;
        if (className_.endsWith(ARR_DYN)) {
            int len_ = className_.length();
            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
        } else {
            realClassName_ = className_;
        }
        return !realClassName_.startsWith(ARR);
    }
}
