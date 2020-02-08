package code.expressionlanguage;

import code.expressionlanguage.structs.*;
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

    public static boolean isNotFalseValue(Argument _arg) {
        if (_arg == null) {
            return true;
        }
        if (!(_arg.getStruct() instanceof BooleanStruct)) {
            return true;
        }
        return !_arg.isFalse();
    }
    public static boolean isTrueValue(Argument _arg) {
        if (_arg == null) {
            return false;
        }
        if (!(_arg.getStruct() instanceof BooleanStruct)) {
            return false;
        }
        return _arg.isTrue();
    }
    public static boolean isNullValue(Argument _arg) {
        if (_arg == null) {
            return false;
        }
        return _arg.object == NullStruct.NULL_VALUE;
    }
    public static boolean isNotDisplayableValue(Argument _arg) {
        if (_arg == null) {
            return false;
        }
        return !(_arg.object instanceof DisplayableStruct);
    }

    public boolean isNull() {
        return object == NullStruct.NULL_VALUE;
    }

    public String getString() {
        return ((CharSequenceStruct)object).toStringInstance();
    }
    public char getChar() {
        return ((CharStruct)object).getChar();
    }
    public long getNumber() {
        return ((NumberStruct)object).longStruct();
    }
    public double getDouble() {
        return ((NumberStruct)object).doubleStruct();
    }

    public long getLong() {
        return ((NumberStruct)object).longStruct();
    }
    public int getInt() {
        return ((NumberStruct)object).intStruct();
    }
    public boolean isTrue() {
        return ((BooleanStruct)object).getInstance();
    }
    public boolean isFalse() {
        return !((BooleanStruct)object).getInstance();
    }

    public void setObject(char _object) {
        object = new CharStruct(_object);
    }
    public void setObject(boolean _object) {
        object = new BooleanStruct(_object);
    }
    public void setObject(String _object) {
        object = new StringStruct(_object);
    }

    public void setObject(int _object) {
        object = new IntStruct(_object);
    }

    public String getObjectClassName(ContextEl _context) {
        return _context.getStandards().getStructClassName(object, _context);
    }

}
