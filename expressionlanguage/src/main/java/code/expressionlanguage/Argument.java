package code.expressionlanguage;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.NullStruct;
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

    public static Struct getNullable(Argument _arg) {
        if (_arg != null) {
            return _arg.getStruct();
        }
        return null;
    }

    public static Struct getNull(Struct _arg) {
        if (_arg != null) {
            return _arg;
        }
        return NullStruct.NULL_VALUE;
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
        return !BooleanStruct.of(false).sameReference(_arg.getStruct());
    }
    public static boolean isTrueValue(Argument _arg) {
        if (_arg == null) {
            return false;
        }
        return BooleanStruct.of(true).sameReference(_arg.getStruct());
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
        return BooleanStruct.of(true).sameReference(object);
    }
    public boolean isFalse() {
        return BooleanStruct.of(false).sameReference(object);
    }

    public String getObjectClassName(ContextEl _context) {
        return _context.getStandards().getStructClassName(object, _context);
    }

}
