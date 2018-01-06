package code.expressionlanguage.opers.util;

import code.util.Numbers;

public abstract class NumberStruct implements Struct {

    @Override
    public final boolean sameReference(Struct _other) {
        if (!(_other instanceof NumberStruct)) {
            if (_other instanceof CharStruct) {
                CharStruct other_ = (CharStruct) _other;
                Number nb_ = getInstance();
                if (nb_ instanceof Float) {
                    return false;
                }
                if (nb_ instanceof Double) {
                    return false;
                }
                return Numbers.eq(nb_.longValue(), other_.getInstance());
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
