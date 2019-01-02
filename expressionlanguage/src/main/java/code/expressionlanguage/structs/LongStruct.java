package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;

public final class LongStruct extends NumberStruct {

    private final long value;

    public LongStruct(long _value) {
        value = _value;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasLong();
    }

    @Override
    public Number getInstance() {
        return value;
    }

}
