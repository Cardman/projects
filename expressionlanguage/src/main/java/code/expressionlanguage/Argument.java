package code.expressionlanguage;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ByteStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.DoubleStruct;
import code.expressionlanguage.opers.util.FloatStruct;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class Argument {

    private static final String DOUBLE_SUFFIX = "d";

    private static final String FLOAT_SUFFIX = "f";

    private static final String SHORT_SUFFIX = "s";

    private static final String LONG_SUFFIX = "l";

    private static final String BYTE_SUFFIX = "b";

    private static final String INT_SUFFIX = "i";

    private static final String CHAR_SUFFIX = "c";

    private Struct object = NullStruct.NULL_VALUE;

    public Argument() {
    }

    public Argument(Struct _object) {
        object = _object;
    }
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

    static String extractFromSuffix(String _nb) {
        boolean sub_ = false;
        if (StringList.toLowerCase(_nb).endsWith(INT_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(BYTE_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(LONG_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(SHORT_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(FLOAT_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(DOUBLE_SUFFIX)) {
            sub_ = true;
        }
        if (StringList.toLowerCase(_nb).endsWith(CHAR_SUFFIX)) {
            sub_ = true;
        }
        String nb_ = StringList.removeChars(_nb, '_');
        if (sub_) {
            return nb_.substring(0, nb_.length() - 1);
        }
        return nb_;
    }
    static boolean or(boolean _one, boolean _two) {
        return _one || _two;
    }

    public static Argument numberToArgument(String _nb) {
        String nb_ = extractFromSuffix(_nb);
        Long longValue_ = LgNames.parseLongTen(nb_);
        Number value_;
        if (longValue_ != null) {
            value_ = longValue_;
        } else {
            NumberInfos infos_ = LgNames.trySplitDouble(nb_);
            if (infos_ == null) {
                return null;
            }
            value_ = LgNames.parseDouble(infos_);
        }
        Argument a_ = new Argument();
        if (StringList.quickEq(nb_, StringList.removeChars(_nb, '_'))) {
            a_.object = new LongStruct(value_.longValue());
            return a_;
        }
        if (LgNames.isValidDouble(_nb)) {
            a_.object = new DoubleStruct(value_.doubleValue());
            return a_;
        }
        StringList parts_ = StringList.splitInTwo(_nb, _nb.length() - 1);
        if (StringList.quickEq(StringList.toLowerCase(parts_.last()), INT_SUFFIX)) {
            a_.object = new IntStruct(value_.intValue());
            return a_;
        }
        if (StringList.quickEq(StringList.toLowerCase(parts_.last()), BYTE_SUFFIX)) {
            a_.object = new ByteStruct(value_.byteValue());
            return a_;
        }
        if (StringList.quickEq(StringList.toLowerCase(parts_.last()), LONG_SUFFIX)) {
            a_.object = new LongStruct(value_.longValue());
            return a_;
        }
        if (StringList.quickEq(StringList.toLowerCase(parts_.last()), SHORT_SUFFIX)) {
            a_.object = new ShortStruct(value_.shortValue());
            return a_;
        }
        if (StringList.quickEq(StringList.toLowerCase(parts_.last()), FLOAT_SUFFIX)) {
            a_.object = new FloatStruct(value_.floatValue());
            return a_;
        }
        if (StringList.quickEq(StringList.toLowerCase(parts_.last()), DOUBLE_SUFFIX)) {
            a_.object = new DoubleStruct(value_.doubleValue());
            return a_;
        }
        a_.object = new CharStruct(Character.valueOf((char) value_.longValue()));
        return a_;
    }

    public Struct getStruct() {
        return object;
    }

    public void setStruct(Struct _object) {
        object = _object;
        if (object == null) {
            object = NullStruct.NULL_VALUE;
        }
    }

    public static boolean isNullValue(Argument _arg) {
        if (_arg == null) {
            return false;
        }
        return _arg.isNull();
    }

    public boolean isNull() {
        return object.isNull();
    }

    public Object getRealObject() {
        if (object instanceof CharStruct) {
            return ((CharStruct)object).getChar();
        }
        return object.getInstance();
    }
    public Object getObject() {
        return object.getInstance();
    }

    public void setObject(Character _object) {
        object = new CharStruct(_object);
    }
    public void setObject(Boolean _object) {
        object = new BooleanStruct(_object);
    }
    public void setObject(String _object) {
        object = new StringStruct(_object);
    }
    public void setObject(Byte _object) {
        object = new ByteStruct(_object);
    }
    public void setObject(Short _object) {
        object = new ShortStruct(_object);
    }
    public void setObject(Integer _object) {
        object = new IntStruct(_object);
    }
    public void setObject(Long _object) {
        object = new LongStruct(_object);
    }
    public void setObject(Float _object) {
        object = new FloatStruct(_object);
    }
    public void setObject(Double _object) {
        object = new DoubleStruct(_object);
    }
    public void setObject(Number _object) {
        if (_object instanceof Byte) {
            setObject((Byte)_object);
        } else if (_object instanceof Short) {
            setObject((Short)_object);
        } else if (_object instanceof Integer) {
            setObject((Integer)_object);
        } else if (_object instanceof Long) {
            setObject((Long)_object);
        } else if (_object instanceof Float) {
            setObject((Float)_object);
        } else {
            setObject((Double)_object);
        }
    }

    private ClassArgumentMatching getArgClass(ContextEl _context) {
        return new ClassArgumentMatching(getObjectClassName(_context));
    }

    public String getObjectClassName(ContextEl _context) {
        return _context.getStandards().getStructClassName(object, _context);
    }

    public boolean isIntegerType(ContextEl _context) {
        if (object.isNull()) {
            return false;
        }
        return PrimitiveTypeUtil.isIntegerType(getArgClass(_context), _context);
    }

}
