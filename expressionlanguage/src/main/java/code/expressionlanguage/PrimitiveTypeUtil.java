package code.expressionlanguage;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootedBlock;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignableFrom;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMatching;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.DimComp;
import code.util.CustList;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class PrimitiveTypeUtil {
    public static final String PRIM  = "$";
    public static final String PRIM_BOOLEAN = "$boolean";
    public static final String PRIM_CHAR = "$char";
    public static final String PRIM_BYTE = "$byte";
    public static final String PRIM_SHORT = "$short";
    public static final String PRIM_INT = "$int";
    public static final String PRIM_LONG = "$long";
    public static final String PRIM_FLOAT = "$float";
    public static final String PRIM_DOUBLE = "$double";
    private static final String ARR_CLASS = "[";
    private static final String BOOLEAN = "Z";
    private static final String CHAR = "C";
    private static final String BYTE = "B";
    private static final String SHORT = "S";
    private static final String INTEGER = "I";
    private static final String LONG = "J";
    private static final String FLOAT = "F";
    private static final String DOUBLE = "D";
    private static final String OBJECT = "L";
    private static final String SUFFIX_INSTANCE = ";";
    private static final byte DOUBLE_CASTING = 7;
    private static final byte FLOAT_CASTING = 6;
    private static final byte LONG_CASTING = 5;
    private static final byte INT_CASTING = 4;
    private static final byte CHAR_CASTING = 3;
    private static final byte SHORT_CASTING = 2;
    private static final byte BYTE_CASTING = 1;
    private PrimitiveTypeUtil() {
    }
    public static String getPrettyArrayType(String _className, int _nb) {
        String cl_ = _className;
        for (int i = CustList.FIRST_INDEX; i < _nb; i++) {
            cl_ = getPrettyArrayType(cl_);
        }
        return cl_;
    }
    public static String getArrayType(String _className, int _nb) {
        String cl_ = _className;
        for (int i = CustList.FIRST_INDEX; i < _nb; i++) {
            cl_ = getArrayType(cl_);
        }
        return cl_;
    }
    public static String getArrayType(String _className) {
        if (StringList.quickEq(_className, PRIM_BOOLEAN)) {
            return ARR_CLASS+BOOLEAN;
        }
        if (StringList.quickEq(_className, PRIM_BYTE)) {
            return ARR_CLASS+BYTE;
        }
        if (StringList.quickEq(_className, PRIM_SHORT)) {
            return ARR_CLASS+SHORT;
        }
        if (StringList.quickEq(_className, PRIM_CHAR)) {
            return ARR_CLASS+CHAR;
        }
        if (StringList.quickEq(_className, PRIM_INT)) {
            return ARR_CLASS+INTEGER;
        }
        if (StringList.quickEq(_className, PRIM_LONG)) {
            return ARR_CLASS+LONG;
        }
        if (StringList.quickEq(_className, PRIM_FLOAT)) {
            return ARR_CLASS+FLOAT;
        }
        if (StringList.quickEq(_className, PRIM_DOUBLE)) {
            return ARR_CLASS+DOUBLE;
        }
        if (_className.startsWith(ARR_CLASS)) {
            return ARR_CLASS+_className;
        }
        return ARR_CLASS+OBJECT+_className+SUFFIX_INSTANCE;
    }

    public static String getPrettyArrayType(String _className) {
        return ARR_CLASS+_className;
    }
    
    public static DimComp getQuickComponentBaseType(String _className) {
        int d_ = 0;
        String className_ = _className;
        String comp_ = getQuickComponentType(className_);
        if (comp_ == null) {
            return new DimComp(d_, className_);
        }
        d_++;
        while (true) {
            String res_ = getQuickComponentType(comp_);
            if (res_ == null) {
                return new DimComp(d_, comp_);
            }
            d_++;
            comp_ = res_;
        }
    }

    public static DimComp getComponentBaseType(String _className) {
        int d_ = 0;
        String className_ = _className;
        String comp_ = getComponentType(className_);
        if (comp_ == null) {
            return new DimComp(d_, className_);
        }
        d_++;
        while (true) {
            String res_ = getComponentType(comp_);
            if (res_ == null) {
                return new DimComp(d_, comp_);
            }
            d_++;
            comp_ = res_;
        }
    }

    public static boolean isArrayAssignable(String _arrArg, String _arrParam) {
//        DimComp dArg_ = PrimitiveTypeUtil.getComponentBaseType(_arrArg);
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrArg);
        String a_ = dArg_.getComponent();
//        DimComp dPar_ = PrimitiveTypeUtil.getComponentBaseType(_arrParam);
        DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrParam);
        String className_ = dPar_.getComponent();
        if (StringList.quickEq(className_, Object.class.getName())) {
            if (dPar_.getDim() > dArg_.getDim()) {
                return false;
            }
            return true;
        }
        if (dPar_.getDim() != dArg_.getDim()) {
            return false;
        }
        if (StringList.quickEq(className_, a_)) {
            return true;
        }
        return false;
    }

    public static String getQuickComponentType(String _className) {
        if (!_className.startsWith(ARR_CLASS)) {
            return null;
        }
        return _className.substring(CustList.SECOND_INDEX);
    }

    public static String getComponentType(String _className) {
        if (!_className.startsWith(ARR_CLASS)) {
            return null;
        }
        String compon_ = _className.substring(CustList.SECOND_INDEX);
        if (StringList.quickEq(compon_, BOOLEAN)) {
            return PRIM_BOOLEAN;
        }
        if (StringList.quickEq(compon_, BYTE)) {
            return PRIM_BYTE;
        }
        if (StringList.quickEq(compon_, SHORT)) {
            return PRIM_SHORT;
        }
        if (StringList.quickEq(compon_, CHAR)) {
            return PRIM_CHAR;
        }
        if (StringList.quickEq(compon_, INTEGER)) {
            return PRIM_INT;
        }
        if (StringList.quickEq(compon_, LONG)) {
            return PRIM_LONG;
        }
        if (StringList.quickEq(compon_, FLOAT)) {
            return PRIM_FLOAT;
        }
        if (StringList.quickEq(compon_, DOUBLE)) {
            return PRIM_DOUBLE;
        }
        if (!compon_.startsWith(ARR_CLASS)) {
            compon_ = compon_.substring(CustList.SECOND_INDEX, compon_.length() - 1);
        }
        return compon_;
    }
    public static Object convertObject(ClassArgumentMatching _match, Object _obj) {
        if (_match.matchClass(PRIM_DOUBLE) || _match.matchClass(Double.class)) {
            return ((Number)_obj).doubleValue();
        }
        if (_match.matchClass(PRIM_FLOAT) || _match.matchClass(Float.class)) {
            return ((Number)_obj).floatValue();
        }
        if (_match.matchClass(PRIM_LONG) || _match.matchClass(Long.class)) {
            return ((Number)_obj).longValue();
        }
        if (_match.matchClass(PRIM_INT) || _match.matchClass(Integer.class)) {
            return ((Number)_obj).intValue();
        }
        if (_match.matchClass(PRIM_SHORT) || _match.matchClass(Short.class)) {
            return ((Number)_obj).shortValue();
        }
        if (_match.matchClass(PRIM_BYTE) || _match.matchClass(Byte.class)) {
            return ((Number)_obj).shortValue();
        }
        if (_match.matchClass(PRIM_CHAR) || _match.matchClass(Character.class)) {
            return ((Character)_obj).charValue();
        }
        return _obj;
    }

    public static String getPrettyArrayClass(String _class) {
        DimComp d_ = getComponentBaseType(_class);
        String compo_ = d_.getComponent();
        return getPrettyArrayType(compo_, d_.getDim());
    }

    public static String getAliasArrayClass(Class<?> _class) {
        String className_ = _class.getName();
        DimComp d_ = getComponentBaseType(className_);
        String compo_ = d_.getComponent();
        return getPrettyArrayType(compo_, d_.getDim());
    }

    public static String getArrayClass(String _class) {
//        return _class;
        DimComp d_ = getQuickComponentBaseType(_class);
        String compo_ = d_.getComponent();
        return getArrayType(compo_, d_.getDim());
    }

//    public static String getAliasClass(String _className) {
//        if (StringList.quickEq(_className,DOUBLE)) {
//            return PRIM_DOUBLE;
//        }
//        if (StringList.quickEq(_className,FLOAT)) {
//            return PRIM_FLOAT;
//        }
//        if (StringList.quickEq(_className,LONG)) {
//            return PRIM_LONG;
//        }
//        if (StringList.quickEq(_className,INTEGER)) {
//            return PRIM_INT;
//        }
//        if (StringList.quickEq(_className,SHORT)) {
//            return PRIM_SHORT;
//        }
//        if (StringList.quickEq(_className,BYTE)) {
//            return PRIM_BYTE;
//        }
//        if (StringList.quickEq(_className,CHAR)) {
//            return PRIM_CHAR;
//        }
//        if (StringList.quickEq(_className,BOOLEAN)) {
//            return PRIM_BOOLEAN;
//        }
//        return _className;
//    }
//    public static String getRevertedAliasClass(String _className) {
//        if (StringList.quickEq(_className,PRIM_DOUBLE)) {
//            return DOUBLE;
//        }
//        if (StringList.quickEq(_className,PRIM_FLOAT)) {
//            return FLOAT;
//        }
//        if (StringList.quickEq(_className,PRIM_LONG)) {
//            return LONG;
//        }
//        if (StringList.quickEq(_className,PRIM_INT)) {
//            return INTEGER;
//        }
//        if (StringList.quickEq(_className,PRIM_SHORT)) {
//            return SHORT;
//        }
//        if (StringList.quickEq(_className,PRIM_BYTE)) {
//            return BYTE;
//        }
//        if (StringList.quickEq(_className,PRIM_CHAR)) {
//            return CHAR;
//        }
//        if (StringList.quickEq(_className,PRIM_BOOLEAN)) {
//            return BOOLEAN;
//        }
//        return _className;
//    }
    public static boolean canBeUseAsArgument(ClassName _param, ClassName _arg, Classes _classes) {
        return canBeUseAsArgument(_param.getName(), _arg.getName(), _classes);
    }
    public static boolean canBeUseAsArgument(String _param, String _arg, Classes _classes) {
        if (StringList.quickEq(_param, OperationNode.VOID_RETURN)) {
            return false;
        }
        ClassArgumentMatching param_ = new ClassArgumentMatching(_param);
        if (_arg == null) {
            try {
                if (param_.isPrimitive()) {
                    return false;
                }
            } catch (RuntimeClassNotFoundException _0) {
            }
            return true;
        }
        AssignableFrom a_ = isAssignableFromCust(_param, _arg, _classes);
        if (a_ == AssignableFrom.YES) {
            return true;
        }
        if (a_ == AssignableFrom.NO) {
            return false;
        }
        Class<?> clParam_ = param_.getClazz();
        return canBeUseAsArgument(clParam_, new ClassArgumentMatching(_arg).getClazz());
    }
    public static boolean isAssignableFrom(String _param, String _arg, Classes _classes) {
        AssignableFrom a_ = isAssignableFromCust(_param, _arg, _classes);
        if (a_ == AssignableFrom.YES) {
            return true;
        }
        if (a_ == AssignableFrom.NO) {
            return false;
        }
        Class<?> param_ = new ClassArgumentMatching(_param).getClazz();
        Class<?> arg_ = new ClassArgumentMatching(_arg).getClazz();
        return param_.isAssignableFrom(arg_);
    }

    public static AssignableFrom isAssignableFromCust(String _className,String _a, Classes _classes) {
        if (StringList.quickEq(_className, Object.class.getName())) {
            return AssignableFrom.YES;
        }
        if (_classes != null) {
//            DimComp dArg_ = PrimitiveTypeUtil.getComponentBaseType(_a);
            DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_a);
            String a_ = dArg_.getComponent();
            RootedBlock clBl_ = _classes.getClassBody(a_);
            if (clBl_ != null) {
//                DimComp dPar_ = PrimitiveTypeUtil.getComponentBaseType(_className);
                DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_className);
                if (dArg_.getDim() > 0 && dPar_.getDim() > 0) {
                    if (isArrayAssignable(_a, _className)) {
                        return AssignableFrom.YES;
                    }
                    return AssignableFrom.NO;
                }
                String className_ = dPar_.getComponent();
//                if (StringList.quickEq(className_, Object.class.getName())) {
//                    if (dPar_.getDim() > dArg_.getDim()) {
//                        return AssignableFrom.NO;
//                    }
//                    return AssignableFrom.YES;
//                }
//                if (dPar_.getDim() != dArg_.getDim()) {
//                    return AssignableFrom.NO;
//                }
                if (StringList.quickEq(className_, a_)) {
                    return AssignableFrom.YES;
                }
                if (clBl_.getAllSuperClasses().containsObj(className_)) {
                    return AssignableFrom.YES;
                }
                return AssignableFrom.NO;
            }
        }
        return AssignableFrom.MAYBE;
    }

    private static boolean canBeUseAsArgument(Class<?> _param, Class<?> _arg) {
        if (_arg == boolean.class || _arg == Boolean.class) {
            if (!_param.isAssignableFrom(Boolean.class) && _param != boolean.class) {
                return false;
            }
            return true;
        }
        if (_param == Object.class && _arg.isPrimitive()) {
            return true;
        }
        if (_param.isAssignableFrom(_arg)) {
            return true;
        }
        Class<?> clMatch_ = PrimitiveTypeUtil.toPrimitive(_arg, true);
        if (clMatch_.isPrimitive()) {
            if (_arg.isPrimitive()) {
                CustList<Class<?>> gt_ = PrimitiveTypeUtil.getOrdersGreaterEqThan(clMatch_);
                if (isPureNumberClass(clMatch_) && _param == Number.class) {
                    return true;
                }
                Class<?> prim_ = PrimitiveTypeUtil.toPrimitive(_param, true);
                boolean contained_ = false;
                for (Class<?> c: gt_) {
                    if (c == prim_) {
                        contained_ = true;
                        break;
                    }
                }
                if (!contained_) {
                    return false;
                }
                return true;
            }
            if (!_param.isAssignableFrom(_arg)) {
                if (!_param.isPrimitive()) {
                    return false;
                }
                CustList<Class<?>> gt_ = PrimitiveTypeUtil.getOrdersGreaterEqThan(clMatch_);
                Class<?> prim_ = _param;
                boolean contained_ = false;
                for (Class<?> c: gt_) {
                    if (c == prim_) {
                        contained_ = true;
                        break;
                    }
                }
                if (!contained_) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static CustList<Class<?>> getOrdersGreaterEqThan(Class<?> _class) {
        CustList<Class<?>> primitives_ = new CustList<Class<?>>();
        primitives_.add(double.class);
        primitives_.add(float.class);
        primitives_.add(long.class);
        primitives_.add(int.class);
        primitives_.add(char.class);
        primitives_.add(short.class);
        primitives_.add(byte.class);
        CustList<Class<?>> gt_ = new CustList<Class<?>>();
        for (Class<?> p: primitives_) {
            if (getOrderClass(p) >= getOrderClass(_class)) {
                gt_.add(p);
            }
        }
        return gt_;
    }
    public static int getOrderClass(ClassArgumentMatching _class) {
        try {
            return getOrderClass(_class.getClazz());
        } catch (RuntimeClassNotFoundException _0) {
            return 0;
        }
    }
    public static int getOrderClass(ClassMatching _class) {
        try {
            return getOrderClass(_class.getClazz());
        } catch (RuntimeClassNotFoundException _0) {
            return 0;
        }
    }
    public static boolean isPrimitiveType(String _className) {
        try {
            return ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(_className)).isPrimitive();
        } catch (RuntimeClassNotFoundException _0) {
            return false;
        }
    }
    public static boolean isPrimitiveOrWrapper(String _className) {
        return toPrimitive(new ClassArgumentMatching(_className), false) != null;
    }
    public static boolean isPureNumberClass(ClassArgumentMatching _class) {
        try {
            return isPureNumberClass(_class.getClazz());
        } catch (RuntimeClassNotFoundException _0) {
            return false;
        }
    }
    public static boolean isPureNumberClass(Class<?> _class) {
        if (_class == double.class) {
            return true;
        }
        if (_class == Double.class) {
            return true;
        }
        if (_class == float.class) {
            return true;
        }
        if (_class == Float.class) {
            return true;
        }
        if (_class == long.class) {
            return true;
        }
        if (_class == Long.class) {
            return true;
        }
        if (_class == int.class) {
            return true;
        }
        if (_class == Integer.class) {
            return true;
        }
        if (_class == short.class) {
            return true;
        }
        if (_class == Short.class) {
            return true;
        }
        if (_class == byte.class) {
            return true;
        }
        if (_class == Byte.class) {
            return true;
        }
        return false;
    }
    public static int getOrderClass(Class<?> _class) {
        if (_class == double.class) {
            return DOUBLE_CASTING;
        }
        if (_class == Double.class) {
            return DOUBLE_CASTING;
        }
        if (_class == float.class) {
            return FLOAT_CASTING;
        }
        if (_class == Float.class) {
            return FLOAT_CASTING;
        }
        if (_class == long.class) {
            return LONG_CASTING;
        }
        if (_class == Long.class) {
            return LONG_CASTING;
        }
        if (_class == int.class) {
            return INT_CASTING;
        }
        if (_class == Integer.class) {
            return INT_CASTING;
        }
        if (_class == char.class) {
            return CHAR_CASTING;
        }
        if (_class == Character.class) {
            return CHAR_CASTING;
        }
        if (_class == short.class) {
            return SHORT_CASTING;
        }
        if (_class == Short.class) {
            return SHORT_CASTING;
        }
        if (_class == byte.class) {
            return BYTE_CASTING;
        }
        if (_class == Byte.class) {
            return BYTE_CASTING;
        }
        return 0;
    }
    public static ClassArgumentMatching toPrimitive(ClassArgumentMatching _class, boolean _id) {
        Class<?> native_;
        try {
            native_ = _class.getClazz();
        } catch (RuntimeClassNotFoundException _0_) {
            if (_id) {
                return _class;
            }
            return null;
        }
        if (native_ == Double.class) {
            return new ClassArgumentMatching(PRIM_DOUBLE);
        }
        if (native_ == Float.class) {
            return new ClassArgumentMatching(PRIM_FLOAT);
        }
        if (native_ == Long.class) {
            return new ClassArgumentMatching(PRIM_LONG);
        }
        if (native_ == Integer.class) {
            return new ClassArgumentMatching(PRIM_INT);
        }
        if (native_ == Short.class) {
            return new ClassArgumentMatching(PRIM_SHORT);
        }
        if (native_ == Byte.class) {
            return new ClassArgumentMatching(PRIM_BYTE);
        }
        if (native_ == Character.class) {
            return new ClassArgumentMatching(PRIM_CHAR);
        }
        if (_id) {
            return _class;
        }
        return null;
    }
    public static Class<?> toPrimitive(Class<?> _class, boolean _id) {
        if (_class == Double.class) {
            return double.class;
        }
        if (_class == Float.class) {
            return float.class;
        }
        if (_class == Long.class) {
            return long.class;
        }
        if (_class == Integer.class) {
            return int.class;
        }
        if (_class == Short.class) {
            return short.class;
        }
        if (_class == Byte.class) {
            return byte.class;
        }
        if (_class == Character.class) {
            return char.class;
        }
        if (_id) {
            return _class;
        }
        return null;
    }
    public static ClassArgumentMatching toWrapper(ClassArgumentMatching _class, boolean _id) {
        Class<?> native_;
        try {
            native_ = _class.getClazz();
        } catch (RuntimeClassNotFoundException _0_) {
            if (_id) {
                return _class;
            }
            return null;
        }
        if (native_ == double.class) {
            return new ClassArgumentMatching(Double.class.getName());
        }
        if (native_ == float.class) {
            return new ClassArgumentMatching(Float.class.getName());
        }
        if (native_ == long.class) {
            return new ClassArgumentMatching(Long.class.getName());
        }
        if (native_ == int.class) {
            return new ClassArgumentMatching(Integer.class.getName());
        }
        if (native_ == char.class) {
            return new ClassArgumentMatching(Character.class.getName());
        }
        if (native_ == short.class) {
            return new ClassArgumentMatching(Short.class.getName());
        }
        if (native_ == byte.class) {
            return new ClassArgumentMatching(Byte.class.getName());
        }
        if (_id) {
            return _class;
        }
        return null;
    }
    public static Class<?> toWrapper(Class<?> _class, boolean _id) {
        if (_class == double.class) {
            return Double.class;
        }
        if (_class == float.class) {
            return Float.class;
        }
        if (_class == long.class) {
            return Long.class;
        }
        if (_class == int.class) {
            return Integer.class;
        }
        if (_class == char.class) {
            return Character.class;
        }
        if (_class == short.class) {
            return Short.class;
        }
        if (_class == byte.class) {
            return Byte.class;
        }
        if (_id) {
            return _class;
        }
        return null;
    }

    public static Object defaultValue(String _class) {
        ClassArgumentMatching cl_ = new ClassArgumentMatching(_class);
        if (cl_.isPrimitive()) {
            if (StringList.quickEq(cl_.getName(), PrimitiveTypeUtil.PRIM_BOOLEAN)) {
                return false;
            }
            return convert(cl_.getClazz(), 0);
        }
        return null;
    }
    public static Object convert(Class<?> _toClass, Object _arg) {
        Class<?> prim_ = toPrimitive(_toClass, true);
        if (prim_ == double.class) {
            if (_arg instanceof Character) {
                return (double)((Character)_arg).charValue();
            }
            return ((Number)_arg).doubleValue();
        }
        if (prim_ == float.class) {
            if (_arg instanceof Character) {
                return (float)((Character)_arg).charValue();
            }
            return ((Number)_arg).floatValue();
        }
        if (prim_ == long.class) {
            if (_arg instanceof Character) {
                return (long)((Character)_arg).charValue();
            }
            return ((Number)_arg).longValue();
        }
        if (prim_ == int.class) {
            if (_arg instanceof Character) {
                return (int)((Character)_arg).charValue();
            }
            return ((Number)_arg).intValue();
        }
        if (prim_ == char.class) {
            if (_arg instanceof Character) {
                return _arg;
            }
            return (char)((Number)_arg).longValue();
        }
        if (prim_ == short.class) {
            if (_arg instanceof Character) {
                return (short)((Character)_arg).charValue();
            }
            return ((Number)_arg).shortValue();
        }
        if (prim_ == byte.class) {
            if (_arg instanceof Character) {
                return (byte)((Character)_arg).charValue();
            }
            return ((Number)_arg).byteValue();
        }
        return null;
    }
    public static boolean isIntegerType(Class<?> _class) {
        Class<?> prim_ = toPrimitive(_class, true);
        if (prim_ == long.class) {
            return true;
        }
        if (prim_ == int.class) {
            return true;
        }
        if (prim_ == char.class) {
            return true;
        }
        if (prim_ == short.class) {
            return true;
        }
        if (prim_ == byte.class) {
            return true;
        }
        return false;
    }
}
