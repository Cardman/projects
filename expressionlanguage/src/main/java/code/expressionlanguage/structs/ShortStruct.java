package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;

public final class ShortStruct extends NumberStruct {

    private final short value;

    public ShortStruct(short _value) {
        value = _value;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasShort();
    }

    @Override
    public Number getInstance() {
        return value;
    }

}
