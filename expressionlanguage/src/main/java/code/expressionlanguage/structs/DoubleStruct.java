package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;

public final class DoubleStruct extends NumberStruct {

    private final double value;

    public DoubleStruct(double _value) {
        value = _value;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasDouble();
    }

    @Override
    public Number getInstance() {
        return value;
    }

}
