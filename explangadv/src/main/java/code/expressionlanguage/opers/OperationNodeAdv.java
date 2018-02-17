package code.expressionlanguage.opers;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtilAdv;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ArgumentsGroup;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassArgumentMatchingAdv;
import code.expressionlanguage.opers.util.ClassMatching;
import code.expressionlanguage.opers.util.ClassMethodIdResult;
import code.expressionlanguage.opers.util.ConstructorInfoAdv;
import code.expressionlanguage.opers.util.MethodInfo;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.ParametersGroup;
import code.expressionlanguage.opers.util.Parametrables;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.types.NativeTypeUtil;
import code.serialize.ConverterMethod;
import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.NoSuchDeclaredConstructorException;
import code.serialize.exceptions.NoSuchDeclaredFieldException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class OperationNodeAdv {
    public static final String VOID_RETURN = "$void";
    public static final String METH_NAME = "name";
    public static final String METH_ORDINAL = "ordinal";
    public static final String METH_VALUES = "values";
    public static final String METH_VALUEOF = "valueOf";

    protected static final char ESCAPE_META_CHAR = '\\';
    protected static final char DELIMITER_CHAR = 39;
    protected static final char DELIMITER_STRING = 34;
    protected static final char UNICODE = 'u';
    protected static final char IND_FORM = 'f';
    protected static final char IND_LINE = 'n';
    protected static final char IND_LINE_FEED = 'r';
    protected static final char IND_TAB = 't';
    protected static final char IND_BOUND = 'b';
    protected static final char ARR_LEFT = '[';
    protected static final char ARR_RIGHT = ']';
    protected static final char PAR_LEFT = '(';
    protected static final char PAR_RIGHT = ')';
    protected static final String PAR_RIGHT_STR = ")";
    protected static final char SEP_ARG = ',';
    protected static final char FIRST_VAR_ARG = '?';
    protected static final char DOT_VAR = '.';
    protected static final char EXTERN_CLASS = '$';
    protected static final char INTERN_CLASS = '$';
    protected static final String SUPER_ACCESS = "super";
    protected static final String CURRENT = "this";
    protected static final String INSTANCE = "new";
    protected static final String STATIC_ACCESS = "static";
    protected static final String INSTANCEOF = "instanceof";
    protected static final String BOOLEAN = "bool";
    protected static final String CAST = "class";
    protected static final char MIN_ENCODE_DIGIT = '0';
    protected static final char MAX_ENCODE_DIGIT = '9';
    protected static final char MIN_ENCODE_LOW_LETTER = 'a';
    protected static final char MAX_ENCODE_LOW_LETTER = 'f';
    protected static final char MIN_ENCODE_UPP_LETTER = 'A';
    protected static final char MAX_ENCODE_UPP_LETTER = 'F';
    protected static final String CURRENT_INTANCE = "$this";
    protected static final String STATIC_CALL = "$$";
    protected static final String VAR_ARG = "vararg";
    protected static final String FIRST_OPT = "firstopt";

    protected static final String CLASS_CHOICE_PREF = "$classchoice$";

    protected static final String FCT = "(";

    protected static final String ARR = "[";

    protected static final String ARR_DYN = "[]";

    protected static final String DOT = ".";

    protected static final String NEG_BOOL = "!";

    protected static final String UNARY_PLUS = "+";

    protected static final String UNARY_MINUS = "-";

    protected static final String MULT = "*";

    protected static final String DIV = "/";

    protected static final String MOD = "%";

    protected static final String PLUS = "+";

    protected static final String MINUS = "-";

    protected static final String LOWER_EQ = "<=";

    protected static final String LOWER = "<";

    protected static final String GREATER_EQ = ">=";

    protected static final String GREATER = ">";

    protected static final String EQ = "=";

    protected static final String DIFF = "!=";

    protected static final String AND = "&";

    protected static final String OR = "|";
    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";
    protected static final String SPACE = " ";
    protected static final String RETURN_TAB = "\n\t";

    protected static final String VARARG_SUFFIX = "...";

    private static final int QUICK_OP = 3;

    private MethodOperation parent;

    private OperationNode nextSibling;

    private Argument argument;

    private OperationsSequence operations;

    private int indexInEl;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    private boolean vararg;
    private boolean firstOptArg;

    private ClassArgumentMatching resultClass;

    private boolean staticBlock;
    static Field getDeclaredField(ContextEl _cont,ClassArgumentMatchingAdv _class, String _name) {
        Class<?> class_ = _class.getClazz();
        StringList traces_ = new StringList();
        while (class_ != null) {
            try {
                return class_.getDeclaredField(_name);
            } catch (NoSuchFieldException _0) {
                String trace_ = StringList.concat(class_.getName(),DOT,_name);
                traces_.add(trace_);
            }
            class_ = class_.getSuperclass();
        }
        throw new NoSuchDeclaredFieldException(StringList.concat(traces_.join(RETURN_TAB),RETURN_LINE,_cont.joinPages()));
    }
    static Constructor<?> getDeclaredConstructor(ContextEl _conf, int _varargOnly, int _offsetIncr, ClassArgumentMatchingAdv _class,
            ClassArgumentMatching... _args) {
        String className_ = _class.getName();
        for (ClassArgumentMatching c:_args) {
            if (c.matchVoid(_conf)) {
                throw new VoidArgumentException(StringList.concat(className_,RETURN_LINE,_conf.joinPages()));
            }
        }
        CustList<Constructor<?>> possibleConstructors_ = new CustList<Constructor<?>>();
        for (Constructor<?> m: _class.getClazz().getDeclaredConstructors()) {
            if (_varargOnly > -1) {
                if (!m.isVarArgs()) {
                    continue;
                }
            }
            Class<?>[] params_ = m.getParameterTypes();
            int nbParams_ = m.getTypeParameters().length;
            ClassMatching[] p_ = new ClassMatching[params_.length];
            int i_ = CustList.FIRST_INDEX;
            for (Class<?> c: params_) {
                Type type_ = m.getGenericParameterTypes()[i_];
                String pre_ = NativeTypeUtil.getFormattedType(c.getName(), type_.toString(), nbParams_, type_);
                p_[i_] = new ClassMatching(pre_);
                i_++;
            }
            if (!OperationNode.isPossibleMethod(_conf, className_, _varargOnly, m.isVarArgs(), p_, _args)) {
                continue;
            }
            possibleConstructors_.add(m);
        }
        if (possibleConstructors_.isEmpty()) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new NoSuchDeclaredConstructorException(StringList.concat(className_,RETURN_LINE,_conf.joinPages()));
        }
        if (possibleConstructors_.size() == CustList.ONE_ELEMENT) {
            return possibleConstructors_.first();
        }
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        String glClass_ = _conf.getLastPage().getGlobalClass();
        if (glClass_ != null) {
            for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                map_.put(t.getName(), t.getConstraints());
            }
        }
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_, _args);
        gr_.setGlobalClass(glClass_);
        Parametrables<ConstructorInfoAdv> signatures_ = new Parametrables<ConstructorInfoAdv>();
//        for (Constructor<?> m: possibleConstructors_) {
//            ParametersGroup p_ = new ParametersGroup();
//            int nbParams_ = m.getTypeParameters().length;
//            int i_ = CustList.FIRST_INDEX;
//            for (Class<?> c: m.getParameterTypes()) {
//                Type type_ = m.getGenericParameterTypes()[i_];
//                String pre_ = NativeTypeUtil.getFormattedType(c.getName(), type_.toString(), nbParams_, type_);
//                p_.add(new ClassMatching(pre_));
//                i_++;
//            }
//            ConstructorInfo mloc_ = new ConstructorInfo();
//            mloc_.setClassName(className_);
//            mloc_.setMethod(m);
//            mloc_.setParameters(p_);
//            signatures_.add(mloc_);
//        }
        //signatures_.size() >= 2
//                OperationNode.sortCtors(signatures_, gr_);
        if (gr_.isAmbigous()) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new NoSuchDeclaredConstructorException(StringList.concat(className_,RETURN_LINE,_conf.joinPages()));
        }
        return signatures_.first().getMethod();
    }
    static Method getDeclaredMethod(boolean _failIfError, ContextEl _cont, int _varargOnly, boolean _staticContext, ClassArgumentMatchingAdv _class, String _name, ClassArgumentMatching... _argsClass) {
        for (ClassArgumentMatching c:_argsClass) {
            if (c.matchVoid(_cont)) {
                throw new VoidArgumentException(StringList.concat(_class.getName(),DOT,_name,RETURN_LINE,_cont.joinPages()));
            }
        }
        ClassMethodIdResult resInst_ = getDeclaredMethodLoop(_cont, _varargOnly, false, _class, _name, _argsClass);
        ClassMethodIdResult resStatic_ = getDeclaredMethodLoop(_cont, _varargOnly, true, _class, _name, _argsClass);
        return getFoundMethod(_failIfError, _cont, _staticContext, resInst_, resStatic_, _class, _name, _argsClass);
    }
    private static Method getFoundMethod(boolean _failIfError, ContextEl _cont, boolean _staticContext,
            ClassMethodIdResult _resInst, ClassMethodIdResult _resStatic,
            ClassArgumentMatchingAdv _class, String _name, ClassArgumentMatching... _argsClass) {
        boolean foundInst_ = false;
        if (!_staticContext) {
            if (_resInst.getStatus() == SearchingMemberStatus.UNIQ) {
                foundInst_ = true;
            }
        }
//        if (foundInst_) {
//            return _resInst.getMethod();
//        }
        if (!_staticContext && _cont.isAmbigous() && _failIfError) {
            String clCurName_ = _class.getName();
            StringBuilder trace_ = new StringBuilder(clCurName_).append(DOT).append(_name).append(PAR_LEFT);
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _argsClass) {
                classesNames_.add(c.getName());
            }
            trace_.append(classesNames_.join(SEP_ARG));
            trace_.append(PAR_RIGHT);
            throw new NoSuchDeclaredMethodException(StringList.concat(trace_,RETURN_LINE,_cont.joinPages()));
        }
//        if (_resStatic.getStatus() == SearchingMemberStatus.UNIQ) {
//            return _resStatic.getMethod();
//        }
        if (!_failIfError) {
            return null;
        }
        if (_resInst.getStatus() == SearchingMemberStatus.UNIQ) {
            //static access
            throw new StaticAccessException(_cont.joinPages());
        }
        String clCurName_ = _class.getName();
        StringBuilder trace_ = new StringBuilder(clCurName_).append(DOT).append(_name).append(PAR_LEFT);
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(c.getName());
        }
        trace_.append(classesNames_.join(SEP_ARG));
        trace_.append(PAR_RIGHT);
        throw new NoSuchDeclaredMethodException(StringList.concat(trace_,RETURN_LINE,_cont.joinPages()));
    }
    private static ClassMethodIdResult getDeclaredMethodLoop(ContextEl _cont, int _varargOnly, boolean _static, ClassArgumentMatchingAdv _class,
            String _name, ClassArgumentMatching... _argsClass) {
        StringList classNames_ = Templates.getAllGenericSuperTypes(_class.getName(), _cont);
        for (String c: classNames_) {
            Class<?> cl_ = PrimitiveTypeUtilAdv.getSingleNativeClass(c);
            CustList<Method> possibleMethods_ = new CustList<Method>(cl_.getDeclaredMethods());
            ClassMethodIdResult res_ = getResult(_cont, _varargOnly, _static, c, possibleMethods_, _name, _argsClass);
            if (res_.getStatus() == SearchingMemberStatus.ZERO) {
                continue;
            }
            return res_;
        }
        ClassMethodIdResult res_ = new ClassMethodIdResult();
        res_.setStatus(SearchingMemberStatus.ZERO);
        return res_;
    }
    private static ClassMethodIdResult getResult(ContextEl _conf, int _varargOnly, boolean _static, String _class,
            CustList<Method> _methods,
            String _name, ClassArgumentMatching... _argsClass) {
        CustList<Method> possibleMethods_ = new CustList<Method>();
        for (Method m: _methods) {
            if (_static) {
                if (!Modifier.isStatic(m.getModifiers())) {
                    continue;
                }
            } else {
                if (Modifier.isStatic(m.getModifiers())) {
                    continue;
                }
            }
            if (_varargOnly > -1) {
                if (!m.isVarArgs()) {
                    continue;
                }
            }
            if (!StringList.quickEq(m.getName(), _name)) {
                continue;
            }
            Class<?>[] params_ = m.getParameterTypes();
            int nbParams_ = m.getTypeParameters().length;
            ClassMatching[] p_ = new ClassMatching[params_.length];
            int i_ = CustList.FIRST_INDEX;
            for (Class<?> c: params_) {
                Type type_ = m.getGenericParameterTypes()[i_];
                String pre_ = NativeTypeUtil.getFormattedType(c.getName(), type_.toString(), nbParams_, type_);
                p_[i_] = new ClassMatching(pre_);
                i_++;
            }
            if (!OperationNode.isPossibleMethod(_conf, _class, _varargOnly, m.isVarArgs(), p_, _argsClass)) {
                continue;
            }
            possibleMethods_.add(m);
        }
        if (possibleMethods_.isEmpty()) {
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        if (possibleMethods_.size() == CustList.ONE_ELEMENT) {
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.UNIQ);
//            res_.setMethod(possibleMethods_.first());
            return res_;
        }
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        String glClass_ = _conf.getLastPage().getGlobalClass();
        if (glClass_ != null) {
            for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                map_.put(t.getName(), t.getConstraints());
            }
        }
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_, _argsClass);
        gr_.setGlobalClass(glClass_);
        Parametrables<MethodInfo> signatures_ = new Parametrables<MethodInfo>();
        for (Method m: possibleMethods_) {
            ParametersGroup p_ = new ParametersGroup();
            int nbParams_ = m.getTypeParameters().length;
            int i_ = CustList.FIRST_INDEX;
            for (Class<?> c: m.getParameterTypes()) {
                Type type_ = m.getGenericParameterTypes()[i_];
                String pre_ = NativeTypeUtil.getFormattedType(c.getName(), type_.toString(), nbParams_, type_);
                p_.add(new ClassMatching(pre_));
                i_++;
            }
            MethodInfo mloc_ = new MethodInfo();
//            mloc_.setMethod(m);
            mloc_.setClassName(_class);
            mloc_.setStatic(Modifier.isStatic(m.getModifiers()));
            mloc_.setParameters(p_);
            Type type_ = m.getGenericReturnType();
            String pre_ = NativeTypeUtil.getFormattedType(m.getReturnType().getName(), type_.toString(), nbParams_, type_);
            mloc_.setReturnType(pre_);
            signatures_.add(mloc_);
        }
        _conf.setAmbigous(false);
        OperationNode.sortFct(signatures_, gr_);
        if (gr_.isAmbigous()) {
            _conf.setAmbigous(true);
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        ClassMethodIdResult res_ = new ClassMethodIdResult();
        res_.setStatus(SearchingMemberStatus.UNIQ);
//        res_.setMethod(signatures_.first().getMethod());
        return res_;
    }
    static Argument newInstance(ContextEl _conf, Argument _need, int _offsetIncr, boolean _natvararg, Constructor<?> _const, String _className, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        checkArgumentsForInvoking(_conf, _natvararg, toClassNames(_const.getParameterTypes()), args_);
        try {
            Argument a_ = new Argument();
            Object o_ = ConverterMethod.newInstance(_const, adaptedArgs(_const.getParameterTypes(), args_));
            a_.setStruct(new StdStruct(o_, _className));
            return a_;
        } catch (InvokingException _0) {
            String err_ = _conf.getStandards().getAliasError();
            throw new InvokeException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
        } catch (Throwable _0) {
            String err_ = _conf.getStandards().getAliasError();
            throw new ErrorCausingException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
        }
    }

    static Struct invokeMethod(ContextEl _cont,int _offsetIncr, boolean _natvararg, String _className, Method _method, Object _instance, String _classRet, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        checkArgumentsForInvoking(_cont, _natvararg, toClassNames(_method.getParameterTypes()), args_);
        try {
            Object o_ = ConverterMethod.invokeMethod(_method, _instance, adaptedArgs(_method.getParameterTypes(), args_));
            if (o_ == null) {
                return NullStruct.NULL_VALUE;
            }
            if (o_ instanceof String) {
                return new StringStruct((String) o_);
            }
            return StdStruct.wrapStd(o_, _classRet);
        } catch (InvokingException _0) {
            String err_ = _cont.getStandards().getAliasError();
            throw new InvokeException(_cont.joinPages(), new StdStruct(new CustomError(_cont.joinPages()),err_));
        } catch (Throwable _0) {
            String err_ = _cont.getStandards().getAliasError();
            throw new ErrorCausingException(_cont.joinPages(), new StdStruct(new CustomError(_cont.joinPages()),err_));
        }
    }
    static void checkArgumentsForInvoking(ContextEl _cont,boolean _natvararg, StringList _params,Struct... _args) {
        int len_ = _params.size();
        if (_natvararg) {
            len_--;
        }
        StringList traces_ = new StringList();
        for (int i = 0; i < len_; i++) {
            if (PrimitiveTypeUtilAdv.primitiveTypeNullObject(_params.get(i), _args[i], _cont)) {
                traces_.add(StringList.concat(Long.toString(i),RETURN_LINE,_params.get(i),RETURN_LINE,null));
            }
        }
        LgNames stds_ = _cont.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (!traces_.isEmpty()) {
            throw new InvokeException(new StdStruct(new CustomError(StringList.concat(traces_.join(SEP_ARG),RETURN_LINE,_cont.joinPages())),null_));
        }
    }
    static StringList toClassNames(Class<?>[] _params) {
        StringList params_ = new StringList();
        for (Class<?> c: _params) {
            if (c.isPrimitive()) {
                params_.add(StringList.concat(PrimitiveTypeUtilAdv.PRIM,c.getName()));
            } else {
                params_.add(PrimitiveTypeUtilAdv.getAliasArrayClass(c));
            }
        }
        return params_;
    }
    static Object[] adaptedArgs(Class<?>[] _params,Struct... _args) {
        int len_ = _params.length;
        Object[] args_ = new Object[len_];
        for (int i = 0; i < len_; i++) {
            Struct argStruct_ = _args[i];
            if (argStruct_.isNull()) {
                continue;
            }
            Object a_ = argStruct_.getInstance();
            Class<?> p_ = _params[i];
            if (p_ == double.class || p_ == Double.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).doubleValue();
                }
            } else if (p_ == float.class || p_ == Float.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).floatValue();
                }
            } else if (p_ == long.class || p_ == Long.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).longValue();
                }
            } else if (p_ == int.class || p_ == Integer.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).intValue();
                }
            } else if (p_ == short.class || p_ == Short.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).shortValue();
                }
            } else if (p_ == byte.class || p_ == Byte.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).byteValue();
                }
            } else {
                args_[i] = a_;
            }
        }
        return args_;
    }

    static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }
    static boolean canBeUsed(AccessibleObject _field, ContextEl _conf) {
        if (_field instanceof Member) {
            if (Modifier.isPublic(((Member)_field).getModifiers())) {
                return true;
            }
        }
//        return _conf.getAccessValue().canBeUsed(_field, _conf);
        return true;
    }
    static void setAccess(AccessibleObject _field, ContextEl _conf) {
        if (_field instanceof Member) {
            if (Modifier.isPublic(((Member)_field).getModifiers())) {
                return;
            }
        }
//        _conf.getAccessValue().setAccess(_field, _conf);
    }
}
