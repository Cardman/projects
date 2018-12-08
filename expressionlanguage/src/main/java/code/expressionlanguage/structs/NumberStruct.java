package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.util.Numbers;

public abstract class NumberStruct implements DisplayableStruct {

    @Override
    public final Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public static NumberStruct wrapNb(Number _element) {
        if (_element instanceof Double) {
            return new DoubleStruct((Double) _element);
        }
        if (_element instanceof Float) {
            return new FloatStruct((Float) _element);
        }
        if (_element instanceof Long) {
            return new LongStruct((Long) _element);
        }
        if (_element instanceof Integer) {
            return new IntStruct((Integer) _element);
        }
        if (_element instanceof Short) {
            return new ShortStruct((Short) _element);
        }
        return new ByteStruct((Byte) _element);
    }

    @Override
    public final boolean sameReference(Struct _other) {
        if (!(_other instanceof NumberStruct)) {
            return false;
        }
        NumberStruct other_ = (NumberStruct) _other;
        return Numbers.eq(getInstance(), other_.getInstance());
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        return new StringStruct(Numbers.toString(getInstance()));
    }
    @Override
    public abstract Number getInstance();

    @Override
    public boolean isArray() {
        return false;
    }
}
