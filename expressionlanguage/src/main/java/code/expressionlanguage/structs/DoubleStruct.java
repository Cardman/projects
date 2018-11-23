package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.ObjectMap;

public final class DoubleStruct extends NumberStruct {

    private final double value;

    public DoubleStruct(double _value) {
        value = _value;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasPrimDouble();
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
