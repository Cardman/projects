package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;

public final class IntStruct extends NumberStruct {

    private final int value;

    public IntStruct(int _value) {
        value = _value;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasInteger();
    }

    @Override
    public Number getInstance() {
        return value;
    }

}
