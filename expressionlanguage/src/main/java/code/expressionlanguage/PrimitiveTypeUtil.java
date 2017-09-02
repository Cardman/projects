package code.expressionlanguage;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignableFrom;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMatching;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.IndexesComparator;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.Numbers;
import code.util.StringList;
import code.util.TreeMap;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class PrimitiveTypeUtil {
    public static final String NO_SUB_CLASS = "";
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

    public static Struct newCustomArray(String _className, Numbers<Integer> _dims) {
        TreeMap<Numbers<Integer>,Struct> indexesArray_;
        indexesArray_ = new TreeMap<Numbers<Integer>,Struct>(new IndexesComparator());
        Struct[] instanceGl_ = new Struct[_dims.first()];
        Struct output_ = new Struct(instanceGl_, PrimitiveTypeUtil.getPrettyArrayType(_className, _dims.size()));
        Numbers<Integer> dims_ = new Numbers<Integer>();
        indexesArray_.put(new Numbers<Integer>(), output_);
        int glDim_ = _dims.size();
        int i_ = CustList.FIRST_INDEX;
        for (int i : _dims) {
            dims_.add(i);
            glDim_--;
            if (glDim_ == 0) {
                for (Numbers<Integer> k: dims_.getAllIndexes()) {
                    indexesArray_.put(k, new Struct());
                }
                continue;
            }
            String formattedClass_ = PrimitiveTypeUtil.getPrettyArrayType(_className, glDim_);
            for (Numbers<Integer> k: dims_.getAllIndexes()) {
                Struct[] instance_ = new Struct[_dims.get(i_ + 1)];
                Struct value_ = new Struct(instance_, formattedClass_);
                indexesArray_.put(k, value_);
            }
            i_++;
        }
        for (EntryCust<Numbers<Integer>,Struct> e: indexesArray_.entryList()) {
            Numbers<Integer> key_ = e.getKey();
            Struct value_ = e.getValue();
            if (key_.isEmpty()) {
                continue;
            }
            Numbers<Integer> ind_ = new Numbers<Integer>(key_);
            ind_.removeLast();
            int lastIndex_ = key_.last();
            Object instance_ = indexesArray_.getVal(ind_).getInstance();
            if (instance_ != null) {
                ((Struct[])instance_)[lastIndex_] = value_;
            }
        }
        return output_;
    }
    /** Only "object" classes are used as arguments */
    public static StringList getSubclasses(StringList _classNames, Classes _classes) {
        StringList types_ = new StringList();
        for (String i: _classNames) {
            boolean sub_ = true;
            if (StringList.quickEq(i, OperationNode.VOID_RETURN)) {
                for (String j: _classNames) {
                    if (!StringList.quickEq(i, j)) {
                        sub_ = false;
                        break;
                    }
                }
            } else {
                for (String j: _classNames) {
                    if (StringList.quickEq(i, j)) {
                        continue;
                    }
                    if (canBeUseAsArgument(i, j, _classes)) {
                        sub_ = false;
                        break;
                    }
                }
            }
            if (!sub_) {
                continue;
            }
            types_.add(i);
        }
        types_.removeDuplicates();
        return types_;
    }
    public static String getSubslass(StringList _classNames, Classes _classes) {
        boolean hasPrim_ = false;
        boolean hasObj_ = false;
        for (String i: _classNames) {
            if (i.startsWith(PRIM)) {
                hasPrim_ = true;
            } else {
                hasObj_ = true;
            }
        }
        if (hasPrim_ && hasObj_) {
            return NO_SUB_CLASS;
        }
        for (String i: _classNames) {
            boolean sub_ = true;
            if (StringList.quickEq(i, OperationNode.VOID_RETURN)) {
                for (String j: _classNames) {
                    if (!StringList.quickEq(i, j)) {
                        sub_ = false;
                        break;
                    }
                }
            } else {
                for (String j: _classNames) {
                    if (!canBeUseAsArgument(j, i, _classes)) {
                        sub_ = false;
                        break;
                    }
                }
            }
            if (sub_) {
                return i;
            }
        }
        return NO_SUB_CLASS;
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

    public static boolean isArrayAssignable(String _arrArg, String _arrParam, Classes _classes) {
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrArg);
        String a_ = dArg_.getComponent();
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
        RootBlock clArgBl_ = _classes.getClassBody(a_);
        if (clArgBl_.getAllSuperTypes().containsObj(className_)) {
            return true;
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
    public static Struct convertObject(ClassArgumentMatching _match, Object _obj) {
        if (_match.matchClass(PRIM_DOUBLE) || _match.matchClass(Double.class)) {
            return new Struct(((Number)_obj).doubleValue());
        }
        if (_match.matchClass(PRIM_FLOAT) || _match.matchClass(Float.class)) {
            return new Struct(((Number)_obj).floatValue());
        }
        if (_match.matchClass(PRIM_LONG) || _match.matchClass(Long.class)) {
            return new Struct(((Number)_obj).longValue());
        }
        if (_match.matchClass(PRIM_INT) || _match.matchClass(Integer.class)) {
            return new Struct(((Number)_obj).intValue());
        }
        if (_match.matchClass(PRIM_SHORT) || _match.matchClass(Short.class)) {
            return new Struct(((Number)_obj).shortValue());
        }
        if (_match.matchClass(PRIM_BYTE) || _match.matchClass(Byte.class)) {
            return new Struct(((Number)_obj).shortValue());
        }
        if (_match.matchClass(PRIM_CHAR) || _match.matchClass(Character.class)) {
            return new Struct(((Character)_obj).charValue());
        }
        return new Struct(_obj);
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
        DimComp d_ = getQuickComponentBaseType(_class);
        String compo_ = d_.getComponent();
        return getArrayType(compo_, d_.getDim());
    }
    public static boolean canBeUseAsArgument(String _param, String _arg, Classes _classes) {
        if (StringList.quickEq(_param, OperationNode.VOID_RETURN)) {
            return false;
        }
        ClassArgumentMatching param_ = new ClassArgumentMatching(_param);
        if (_arg == null) {
            if (param_.isPrimitive()) {
                return false;
            }
            return true;
        }
        if (StringList.quickEq(_arg, OperationNode.VOID_RETURN)) {
            return false;
        }
        AssignableFrom a_ = isAssignableFromCust(_param, _arg, _classes);
        if (a_ == AssignableFrom.YES) {
            return true;
        }
        if (a_ == AssignableFrom.NO) {
            return false;
        }
        DimComp paramComp_ = getQuickComponentBaseType(param_.getName());
        DimComp argComp_ = getQuickComponentBaseType(_arg);
        Class<?> clParam_ = param_.getClazz();
        Class<?> clArg_ = new ClassArgumentMatching(_arg).getClazz();
        boolean array_ = false;
        if (paramComp_.getDim() == argComp_.getDim()) {
            param_ = new ClassArgumentMatching(paramComp_.getComponent());
            clArg_ = new ClassArgumentMatching(argComp_.getComponent()).getClazz();
            clParam_ = param_.getClazz();
            array_ = paramComp_.getDim() > 0 || argComp_.getDim() > 0;
        }
        return canBeUseAsArgument(clParam_, clArg_, array_);
    }

    public static AssignableFrom isAssignableFromCust(String _param,String _arg, Classes _classes) {
        if (StringList.quickEq(_param, Object.class.getName())) {
            return AssignableFrom.YES;
        }
        if (_classes != null) {
            DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
            String a_ = dArg_.getComponent();
            RootBlock clArgBl_ = _classes.getClassBody(a_);
            if (clArgBl_ != null) {
                DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
                if (dArg_.getDim() > 0 && dPar_.getDim() > 0) {
                    if (isArrayAssignable(_arg, _param, _classes)) {
                        return AssignableFrom.YES;
                    }
                    return AssignableFrom.NO;
                }
                if (dArg_.getDim() != dPar_.getDim()) {
                    return AssignableFrom.NO;
                }
                String className_ = dPar_.getComponent();
                if (StringList.quickEq(className_, a_)) {
                    return AssignableFrom.YES;
                }
                if (clArgBl_.getAllSuperTypes().containsObj(className_)) {
                    return AssignableFrom.YES;
                }
                return AssignableFrom.NO;
            }
            DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
            String p_ = dPar_.getComponent();
            RootBlock clParBl_ = _classes.getClassBody(p_);
            if (clParBl_ != null) {
                return AssignableFrom.NO;
            }
        }
        return AssignableFrom.MAYBE;
    }

    private static boolean canBeUseAsArgument(Class<?> _param, Class<?> _arg, boolean _array) {
        if (_param.isAssignableFrom(_arg)) {
            return true;
        }
        if (!_array) {
            if (_arg == boolean.class || _arg == Boolean.class) {
                if (!_param.isAssignableFrom(Boolean.class) && _param != boolean.class) {
                    return false;
                }
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
                return true;
            }
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
        for (String c: _class.getClassName()) {
            try {
                return getOrderClass(ClassMatching.getSingleNativeClass(c));
            } catch (RuntimeClassNotFoundException _0) {
            }
        }
        return 0;
    }
    public static boolean isPrimitiveOrWrapper(String _className) {
        if (_className.startsWith(PRIM)) {
            return true;
        }
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
    public static ClassMatching toAllPrimitive(ClassMatching _class) {
        if (_class.getClassName().size() != 1) {
            return _class;
        }
        ClassArgumentMatching cl_ = new ClassArgumentMatching(_class.getClassName().first());
        return new ClassMatching(toAllPrimitive(cl_, true).getName());
    }
    public static ClassArgumentMatching toAllPrimitive(ClassArgumentMatching _class, boolean _id) {
        if (_class.matchClass(Boolean.class)) {
            return new ClassArgumentMatching(PRIM_BOOLEAN);
        }
        return toPrimitive(_class, _id);
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
    public static Class<?> toBooleanWrapper(Class<?> _class, boolean _id) {
        if (_class == boolean.class) {
            return Boolean.class;
        }
        return toWrapper(_class, _id);
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

    public static Argument defaultValue(Block _block, Argument _global) {
        if (_block instanceof MethodBlock) {
            MethodBlock m_ = (MethodBlock) _block;
            Object v_ = defaultValue(m_.getReturnType());
            Argument a_ = new Argument();
            a_.setObject(v_);
            return a_;
        }
        if (_block instanceof ConstructorBlock) {
            return _global;
        }
        return Argument.createVoid();
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
