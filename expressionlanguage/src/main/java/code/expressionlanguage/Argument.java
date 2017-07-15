package code.expressionlanguage;
import code.expressionlanguage.exceptions.DynamicNumberFormatException;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class Argument {

    private static final String ARR_PREFIX = "[";

    private static final String DOUBLE_SUFFIX = "d";

    private static final String FLOAT_SUFFIX = "f";

    private static final String SHORT_SUFFIX = "s";

    private static final String LONG_SUFFIX = "l";

    private static final String BYTE_SUFFIX = "b";

    private static final String INT_SUFFIX = "i";

    private static final String CHAR_SUFFIX = "c";

    private Struct object = new Struct();

    private String argClassName;

//    private Class<?> argClass;

    public static Argument[] toArgArray(CustList<Argument> _args) {
        int len_ = _args.size();
        Argument[] args_;
        args_ = new Argument[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            args_[i] = _args.get(i);
        }
        return args_;
    }

    public static Argument createVoid() {
        Argument void_ = new Argument();
        void_.setArgClassName(OperationNode.VOID_RETURN);
        return void_;
    }

    public static Object parseNumber(String _nb) {
        if (StringList.isNumber(_nb)) {
            return Long.parseLong(_nb);
        }
        if (StringList.isDoubleNumber(_nb)) {
            return Double.parseDouble(_nb);
        }
        if (_nb.length() <= CustList.ONE_ELEMENT) {
            throw new DynamicNumberFormatException(_nb);
        }
        StringList parts_ = StringList.splitInTwo(_nb, _nb.length() - 1);
        if (StringList.quickEq(parts_.last().toLowerCase(), INT_SUFFIX)) {
            return Integer.parseInt(parts_.first());
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), BYTE_SUFFIX)) {
            return Byte.parseByte(parts_.first());
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), LONG_SUFFIX)) {
            return Long.parseLong(parts_.first());
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), SHORT_SUFFIX)) {
            return Short.parseShort(parts_.first());
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), FLOAT_SUFFIX)) {
            return Float.parseFloat(parts_.first());
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), DOUBLE_SUFFIX)) {
            return Double.parseDouble(parts_.first());
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), CHAR_SUFFIX)) {
            long l_ = Long.parseLong(parts_.first());
            return Character.valueOf((char) l_);
        }
        throw new DynamicNumberFormatException(_nb);
    }

    public static String getArgClassNameOf(String _nb) {
        if (StringList.isNumber(_nb)) {
            return Long.class.getName();
        }
        if (StringList.isDoubleNumber(_nb)) {
            return Double.class.getName();
        }
        if (_nb.length() <= CustList.ONE_ELEMENT) {
            throw new DynamicNumberFormatException(_nb);
        }
        StringList parts_ = StringList.splitInTwo(_nb, _nb.length() - 1);
        if (!StringList.isDoubleNumber(parts_.first())) {
            throw new DynamicNumberFormatException(_nb);
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), INT_SUFFIX) && StringList.isNumber(parts_.first())) {
            if (StringList.quickEq(parts_.last(), INT_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_INT;
            } else {
                return Integer.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), BYTE_SUFFIX) && StringList.isNumber(parts_.first())) {
            if (StringList.quickEq(parts_.last(), BYTE_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_BYTE;
            } else {
                return Byte.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), LONG_SUFFIX) && StringList.isNumber(parts_.first())) {
            if (StringList.quickEq(parts_.last(), LONG_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_LONG;
            } else {
                return Long.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), SHORT_SUFFIX) && StringList.isNumber(parts_.first())) {
            if (StringList.quickEq(parts_.last(), SHORT_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_SHORT;
            } else {
                return Short.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), FLOAT_SUFFIX) && StringList.isDoubleNumber(parts_.first())) {
            if (StringList.quickEq(parts_.last(), FLOAT_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_FLOAT;
            } else {
                return Float.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), DOUBLE_SUFFIX) && StringList.isDoubleNumber(parts_.first())) {
            if (StringList.quickEq(parts_.last(), DOUBLE_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_DOUBLE;
            } else {
                return Double.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), CHAR_SUFFIX) && StringList.isNumber(parts_.first())) {
            Long.parseLong(parts_.first());
            if (StringList.quickEq(parts_.last(), CHAR_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_CHAR;
            } else {
                return Character.class.getName();
            }
        }
        throw new DynamicNumberFormatException(_nb);
    }
    public static Argument numberToArgument(String _nb) {
        Argument a_ = new Argument();
        if (StringList.isNumber(_nb)) {
            a_.object = new Struct(Long.parseLong(_nb));
            a_.argClassName = Long.class.getName();
            return a_;
        }
        if (StringList.isDoubleNumber(_nb)) {
            a_.object = new Struct(Double.parseDouble(_nb));
            a_.argClassName = Double.class.getName();
            return a_;
        }
        if (_nb.length() <= CustList.ONE_ELEMENT) {
            throw new DynamicNumberFormatException(_nb);
        }
        StringList parts_ = StringList.splitInTwo(_nb, _nb.length() - 1);
        if (!StringList.isDoubleNumber(parts_.first())) {
            throw new DynamicNumberFormatException(_nb);
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), INT_SUFFIX) && StringList.isNumber(parts_.first())) {
            a_.object = new Struct(Integer.parseInt(parts_.first()));
            if (StringList.quickEq(parts_.last(), INT_SUFFIX)) {
                a_.argClassName = PrimitiveTypeUtil.PRIM_INT;
            } else {
                a_.argClassName = Integer.class.getName();
            }
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), BYTE_SUFFIX) && StringList.isNumber(parts_.first())) {
            a_.object = new Struct(Byte.parseByte(parts_.first()));
            if (StringList.quickEq(parts_.last(), BYTE_SUFFIX)) {
                a_.argClassName = PrimitiveTypeUtil.PRIM_BYTE;
            } else {
                a_.argClassName = Byte.class.getName();
            }
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), LONG_SUFFIX) && StringList.isNumber(parts_.first())) {
            a_.object = new Struct(Long.parseLong(parts_.first()));
            if (StringList.quickEq(parts_.last(), LONG_SUFFIX)) {
                a_.argClassName = PrimitiveTypeUtil.PRIM_LONG;
            } else {
                a_.argClassName = Long.class.getName();
            }
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), SHORT_SUFFIX) && StringList.isNumber(parts_.first())) {
            a_.object = new Struct(Short.parseShort(parts_.first()));
            if (StringList.quickEq(parts_.last(), SHORT_SUFFIX)) {
                a_.argClassName = PrimitiveTypeUtil.PRIM_SHORT;
            } else {
                a_.argClassName = Short.class.getName();
            }
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), FLOAT_SUFFIX) && StringList.isDoubleNumber(parts_.first())) {
            a_.object = new Struct(Float.parseFloat(parts_.first()));
            if (StringList.quickEq(parts_.last(), FLOAT_SUFFIX)) {
                a_.argClassName = PrimitiveTypeUtil.PRIM_FLOAT;
            } else {
                a_.argClassName = Float.class.getName();
            }
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), DOUBLE_SUFFIX) && StringList.isDoubleNumber(parts_.first())) {
            a_.object = new Struct(Double.parseDouble(parts_.first()));
            if (StringList.quickEq(parts_.last(), DOUBLE_SUFFIX)) {
                a_.argClassName = PrimitiveTypeUtil.PRIM_DOUBLE;
            } else {
                a_.argClassName = Double.class.getName();
            }
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), CHAR_SUFFIX) && StringList.isNumber(parts_.first())) {
            long l_ = Long.parseLong(parts_.first());
            a_.object = new Struct(Character.valueOf((char) l_));
            if (StringList.quickEq(parts_.last(), CHAR_SUFFIX)) {
                a_.argClassName = PrimitiveTypeUtil.PRIM_CHAR;
            } else {
                a_.argClassName = Character.class.getName();
            }
            return a_;
        }
        throw new DynamicNumberFormatException(_nb);
    }
    
    public boolean matchArgClass(String _className) {
        return StringList.quickEq(argClassName, _className);
    }

//    public boolean matchObjClass(String _className) {
//        String class_ = object.getClassName();
//        if (class_ == null) {
//            return true;
//        }
//        return StringList.quickEq(class_, _className);
//    }

    public Struct getStruct() {
        return object;
    }

    public void setStruct(Struct _object) {
        object = _object;
        if (object == null) {
            object = new Struct();
        }
    }

    public void setStructArgClassName(Struct _object) {
        object = _object;
        if (object == null) {
            object = new Struct();
        } else {
            argClassName = object.getClassName();
        }
    }

    public void setStructArgClassName(Struct _object, String _argClassName) {
        object = _object;
        if (object == null) {
            object = new Struct();
        }
        argClassName = _argClassName;
    }

    public boolean isNull() {
        return object.isNull();
    }

    public Object getObject() {
        return object.getInstance();
    }

    public void setObject(Object _object) {
        if (_object == null) {
            object = new Struct();
        } else {
            object = new Struct(_object);
        }
    }

    public String getObjectClassName() {
        return object.getClassName();
    }

    public String getArgClassName() {
        return argClassName;
    }

    public boolean isPrimitiveClass() {
        if (argClassName.startsWith(PrimitiveTypeUtil.PRIM)) {
            return true;
        }
        try {
            return getArgClass().isPrimitive();
        } catch (RuntimeClassNotFoundException _0_) {
            return false;
        }
    }

    public void setArgClassName(String _argClassName) {
        argClassName = _argClassName;
//        setArgClass(ConstClasses.classForNameNotInit(_argClassName));
    }

    public boolean isArrayClass() {
//        return argClassName.startsWith(ARR_PREFIX);
        return object.isNull() || object.getClassName().startsWith(ARR_PREFIX);
    }

    public Class<?> getArgClass() {
        if (argClassName.startsWith(PrimitiveTypeUtil.PRIM)) {
            return ConstClasses.getPrimitiveClass(argClassName.substring(1));
        }
        return ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(argClassName));
    }

    public boolean isIntegerType() {
        if (object.isNull()) {
            return false;
        }
        try {
            return PrimitiveTypeUtil.isIntegerType(object.getInstance().getClass());
        } catch (RuntimeClassNotFoundException _0) {
            return false;
        }
    }
//
//    public void setArgClass(Class<?> _argClass) {
//        argClass = _argClass;
//    }

}
