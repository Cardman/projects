package code.expressionlanguage;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public final class Argument {

    private static final String UNEXPECTED_TYPE = "";

    private static final String ARR_PREFIX = "[";

    private static final String DOUBLE_SUFFIX = "d";

    private static final String FLOAT_SUFFIX = "f";

    private static final String SHORT_SUFFIX = "s";

    private static final String LONG_SUFFIX = "l";

    private static final String BYTE_SUFFIX = "b";

    private static final String INT_SUFFIX = "i";

    private static final String CHAR_SUFFIX = "c";

    private Struct object = new Struct();

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
        return void_;
    }

    public static String getArgClassNameOf(String _nb) {
        String nb_ = extractFromSuffix(_nb);
        if (!or(Numbers.checkDouble(nb_), Numbers.checkLong(nb_))) {
            return UNEXPECTED_TYPE;
        }
        if (StringList.quickEq(nb_, _nb)) {
            if (Numbers.checkLong(nb_)) {
                return Long.class.getName();
            }
            return Double.class.getName();
        }
        StringList parts_ = StringList.splitInTwo(_nb, _nb.length() - 1);
        boolean long_ = false;
        Number value_;
        if (Numbers.checkLong(nb_)) {
            long_ = true;
            value_ = Long.parseLong(nb_);
        } else {
            value_ = Double.parseDouble(nb_);
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), INT_SUFFIX) && long_) {
            if (!checkedLongBounds(value_.longValue(), Integer.MIN_VALUE, Integer.MAX_VALUE)) {
                return UNEXPECTED_TYPE;
            }
            if (StringList.quickEq(parts_.last(), INT_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_INT;
            } else {
                return Integer.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), BYTE_SUFFIX) && long_) {
            if (!checkedLongBounds(value_.longValue(), Byte.MIN_VALUE, Byte.MAX_VALUE)) {
                return UNEXPECTED_TYPE;
            }
            if (StringList.quickEq(parts_.last(), BYTE_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_BYTE;
            } else {
                return Byte.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), LONG_SUFFIX) && long_) {
            if (StringList.quickEq(parts_.last(), LONG_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_LONG;
            } else {
                return Long.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), SHORT_SUFFIX) && long_) {
            if (!checkedLongBounds(value_.longValue(), Short.MIN_VALUE, Short.MAX_VALUE)) {
                return UNEXPECTED_TYPE;
            }
            if (StringList.quickEq(parts_.last(), SHORT_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_SHORT;
            } else {
                return Short.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), CHAR_SUFFIX) && long_) {
            if (!checkedLongBounds(value_.longValue(), Character.MIN_VALUE, Character.MAX_VALUE)) {
                return UNEXPECTED_TYPE;
            }
            if (StringList.quickEq(parts_.last(), CHAR_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_CHAR;
            } else {
                return Character.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), FLOAT_SUFFIX)) {
            if (!checkedDoubleBounds(value_.doubleValue(), Float.MIN_VALUE, Float.MAX_VALUE)) {
                return UNEXPECTED_TYPE;
            }
            if (StringList.quickEq(parts_.last(), FLOAT_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_FLOAT;
            } else {
                return Float.class.getName();
            }
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), DOUBLE_SUFFIX)) {
            if (StringList.quickEq(parts_.last(), DOUBLE_SUFFIX)) {
                return PrimitiveTypeUtil.PRIM_DOUBLE;
            } else {
                return Double.class.getName();
            }
        }
        return UNEXPECTED_TYPE;
    }

    static boolean checkedLongBounds(long _value, long _min, long _max) {
        if (_value < _min) {
            return false;
        }
        if (_value > _max) {
            return false;
        }
        return true;
    }


    static boolean checkedDoubleBounds(double _value, double _min, double _max) {
        if (_value < _min) {
            return false;
        }
        if (_value > _max) {
            return false;
        }
        return true;
    }

    static String extractFromSuffix(String _nb) {
        boolean sub_ = false;
        if (_nb.toLowerCase().endsWith(INT_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(BYTE_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(LONG_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(SHORT_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(FLOAT_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(DOUBLE_SUFFIX)) {
            sub_ = true;
        }
        if (_nb.toLowerCase().endsWith(CHAR_SUFFIX)) {
            sub_ = true;
        }
        if (sub_) {
            return _nb.substring(0, _nb.length() - 1);
        }
        return _nb;
    }
    static boolean or(boolean _one, boolean _two) {
        return _one || _two;
    }

    public static Argument numberToArgument(String _nb) {
        String nb_ = extractFromSuffix(_nb);
        Number value_;
        if (Numbers.checkLong(nb_)) {
            value_ = Long.parseLong(nb_);
        } else {
            value_ = Double.parseDouble(nb_);
        }
        Argument a_ = new Argument();
        if (Numbers.checkLong(_nb)) {
            a_.object = new Struct(value_.longValue());
            return a_;
        }
        if (Numbers.checkDouble(_nb)) {
            a_.object = new Struct(value_.doubleValue());
            return a_;
        }
        StringList parts_ = StringList.splitInTwo(_nb, _nb.length() - 1);
        if (StringList.quickEq(parts_.last().toLowerCase(), INT_SUFFIX)) {
            a_.object = new Struct(value_.intValue());
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), BYTE_SUFFIX)) {
            a_.object = new Struct(value_.byteValue());
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), LONG_SUFFIX)) {
            a_.object = new Struct(value_.longValue());
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), SHORT_SUFFIX)) {
            a_.object = new Struct(value_.shortValue());
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), FLOAT_SUFFIX)) {
            a_.object = new Struct(value_.floatValue());
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), DOUBLE_SUFFIX)) {
            a_.object = new Struct(value_.doubleValue());
            return a_;
        }
        a_.object = new Struct(Character.valueOf((char) value_.longValue()));
        return a_;
    }

    public Struct getStruct() {
        return object;
    }

    public void setStruct(Struct _object) {
        object = _object;
        if (object == null) {
            object = new Struct();
        }
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

    public boolean isArrayClass() {
        return object.isNull() || object.getClassName().startsWith(ARR_PREFIX);
    }

    public Class<?> getArgClass() {
        return object.getInstance().getClass();
    }

    public boolean isIntegerType() {
        if (object.isNull()) {
            return false;
        }
        return PrimitiveTypeUtil.isIntegerType(object.getInstance().getClass());
    }

}
