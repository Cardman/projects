package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.Numbers;
import code.util.ObjectMap;

public final class CharStruct implements Struct {

    private final char value;

    public CharStruct(char _value) {
        value = _value;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getAliasCharacter();
    }

    @Override
    public Character getInstance() {
        return value;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof CharStruct)) {
            if (_other instanceof NumberStruct) {
                NumberStruct other_ = (NumberStruct) _other;
                Number nb_ = other_.getInstance();
                if (nb_ instanceof Double) {
                    return false;
                }
                if (nb_ instanceof Float) {
                    return false;
                }
                return Numbers.eq(getInstance(), nb_.longValue());
            }
            return false;
        }
        CharStruct other_ = (CharStruct) _other;
        return Numbers.eq(getInstance(), other_.getInstance());
    }

    @Override
    public boolean isArray() {
        return false;
    }
}
