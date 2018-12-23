package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;

public final class FloatStruct extends NumberStruct {

    private final float value;

    public FloatStruct(float _value) {
        value = _value;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasFloat();
    }

    @Override
    public Number getInstance() {
        return value;
    }

}
