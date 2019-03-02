package code.expressionlanguage;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.CharStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class Argument {

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
        return new Argument();
    }

    public Struct getStruct() {
        return object;
    }

    public void setStruct(Struct _object) {
        object = _object;
    }

    public static boolean isFalseValue(Argument _arg) {
        if (_arg == null) {
            return false;
        }
        if (!(_arg.getStruct() instanceof BooleanStruct)) {
            return false;
        }
        return !((BooleanStruct)_arg.getStruct()).getInstance();
    }
    public static boolean isTrueValue(Argument _arg) {
        if (_arg == null) {
            return false;
        }
        if (!(_arg.getStruct() instanceof BooleanStruct)) {
            return false;
        }
        return ((BooleanStruct)_arg.getStruct()).getInstance();
    }
    public static boolean isNullValue(Argument _arg) {
        if (_arg == null) {
            return false;
        }
        return _arg.object == NullStruct.NULL_VALUE;
    }

    public boolean isNull() {
        return object == NullStruct.NULL_VALUE;
    }

    public String getString() {
        return ((CharSequenceStruct)object).getInstance().toString();
    }
    public Number getNumber() {
        return ((NumberStruct)object).getInstance();
    }

    public long getLong() {
        return ((NumberStruct)object).getInstance().longValue();
    }
    public int getInt() {
        return ((NumberStruct)object).getInstance().intValue();
    }
    public boolean isTrue() {
        return ((BooleanStruct)object).getInstance();
    }
    public boolean isFalse() {
        return !((BooleanStruct)object).getInstance();
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

    public void setObject(Integer _object) {
        object = new IntStruct(_object);
    }

    private ClassArgumentMatching getArgClass(ContextEl _context) {
        return new ClassArgumentMatching(getObjectClassName(_context));
    }

    public String getObjectClassName(ContextEl _context) {
        return _context.getStandards().getStructClassName(object, _context);
    }

    public boolean isIntegerType(ContextEl _context) {
        if (object == NullStruct.NULL_VALUE) {
            return false;
        }
        return PrimitiveTypeUtil.isIntegerType(getArgClass(_context), _context);
    }

}
