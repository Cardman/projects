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
    public double doubleValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public short shortValue() {
        return value;
    }

    @Override
    public byte byteValue() {
        return value;
    }

}
