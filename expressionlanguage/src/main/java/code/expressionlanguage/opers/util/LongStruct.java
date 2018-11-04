package code.expressionlanguage.opers.util;

import code.expressionlanguage.ExecutableCode;
import code.util.ObjectMap;

public final class LongStruct extends NumberStruct {

    private final long value;

    public LongStruct(long _value) {
        value = _value;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasPrimLong();
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
