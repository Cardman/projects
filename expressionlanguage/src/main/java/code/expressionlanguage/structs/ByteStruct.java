package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
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
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasPrimByte();
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
