package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;

public final class ByteStruct extends NumberStruct {

    private final byte value;

    public ByteStruct(byte _value) {
        value = _value;
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasByte();
    }

    @Override
    public Number getInstance() {
        return value;
    }

}
