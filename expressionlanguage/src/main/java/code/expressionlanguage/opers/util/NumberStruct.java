package code.expressionlanguage.opers.util;

import code.util.Numbers;

public abstract class NumberStruct implements Struct {
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
            if (_other instanceof CharStruct) {
                CharStruct other_ = (CharStruct) _other;
                Number nb_ = getInstance();
                return Numbers.eq(nb_, (long)other_.getInstance().charValue());
            }
            return false;
        }
        NumberStruct other_ = (NumberStruct) _other;
        return Numbers.eq(getInstance(), other_.getInstance());
    }

    @Override
    public abstract Number getInstance();

    @Override
    public boolean isArray() {
        return false;
    }
}
