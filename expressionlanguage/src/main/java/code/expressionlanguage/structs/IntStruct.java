package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.ObjectMap;

public final class IntStruct extends NumberStruct {

    private final int value;

    public IntStruct(int _value) {
        value = _value;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasPrimInteger();
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
