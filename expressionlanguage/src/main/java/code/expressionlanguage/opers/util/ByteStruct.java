package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.ObjectMap;

public final class ByteStruct extends NumberStruct {

    private final byte value;

    public ByteStruct(byte _value) {
        value = _value;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getAliasByte();
    }

    @Override
    public Number getInstance() {
        return value;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }
}
