package code.expressionlanguage.opers.util;

import code.expressionlanguage.ExecutableCode;
import code.util.ObjectMap;

public final class FloatStruct extends NumberStruct {

    private final float value;

    public FloatStruct(float _value) {
        value = _value;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasPrimFloat();
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
