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
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class Argument {

    private static final String ARR_PREFIX = "[";

    private static final String DOUBLE_SUFFIX = "d";

    private static final String FLOAT_SUFFIX = "f";

    private static final String SHORT_SUFFIX = "s";

    private static final String LONG_SUFFIX = "l";

    private static final String BYTE_SUFFIX = "b";

    private static final String INT_SUFFIX = "i";

    private static final String CHAR_SUFFIX = "c";

    private Struct object = NullStruct.NULL_VALUE;

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
            value_ = Double.parseDouble(nb_);
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
        if (StringList.quickEq(parts_.last().toLowerCase(), INT_SUFFIX)) {
            a_.object = new IntStruct(value_.intValue());
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), BYTE_SUFFIX)) {
            a_.object = new ByteStruct(value_.byteValue());
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), LONG_SUFFIX)) {
            a_.object = new LongStruct(value_.longValue());
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), SHORT_SUFFIX)) {
            a_.object = new ShortStruct(value_.shortValue());
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), FLOAT_SUFFIX)) {
            a_.object = new FloatStruct(value_.floatValue());
            return a_;
        }
        if (StringList.quickEq(parts_.last().toLowerCase(), DOUBLE_SUFFIX)) {
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

    public boolean isNull() {
        return object.isNull();
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
    public void setObject(String _object, String _alias) {
        object = StdStruct.wrapStd(_object, _alias);
    }
    public void setObject(Number _object) {
        object = StdStruct.wrapStd(_object);
    }
    public void setObject(Object _object) {
        object = StdStruct.wrapStd(_object);
    }

    public void setObject(Object _object, String _alias) {
        object = StdStruct.wrapStd(_object, _alias);
    }

    public String getObjectClassName(ContextEl _context) {
        return object.getClassName(_context);
    }

    public boolean isArrayClass(ContextEl _context) {
        return object.isNull() || object.getClassName(_context).startsWith(ARR_PREFIX);
    }

    public ClassArgumentMatching getArgClass(ContextEl _context) {
        return new ClassArgumentMatching(object.getClassName(_context));
    }

    public boolean isIntegerType(ContextEl _context) {
        if (object.isNull()) {
            return false;
        }
        return PrimitiveTypeUtil.isIntegerType(getArgClass(_context), _context);
    }

}
