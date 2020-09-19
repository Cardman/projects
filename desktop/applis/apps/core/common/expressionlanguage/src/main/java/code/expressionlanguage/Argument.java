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

    public static Struct getNullable(Argument _arg) {
        if (_arg != null) {
            return _arg.getStruct();
        }
        return null;
    }

    public static Argument getNullableValue(Argument _arg) {
        if (_arg != null) {
            return _arg;
        }
        return new Argument();
    }

    public static Struct getNull(Struct _arg) {
        if (_arg != null) {
            return _arg;
        }
        return NullStruct.NULL_VALUE;
    }

    public static Struct wrapStr(String _arg) {
        if (_arg != null) {
            return new StringStruct(_arg);
        }
        return NullStruct.NULL_VALUE;
    }
    public static Argument createVoid() {
        return getNullableValue(null);
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
        return !BooleanStruct.isFalse(_arg.getStruct());
    }
    public static boolean isTrueValue(Argument _arg) {
        if (_arg == null) {
            return false;
        }
        return BooleanStruct.isTrue(_arg.getStruct());
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

    public boolean isTrue() {
        return BooleanStruct.isTrue(object);
    }
    public boolean isFalse() {
        return BooleanStruct.isFalse(object);
    }

}
