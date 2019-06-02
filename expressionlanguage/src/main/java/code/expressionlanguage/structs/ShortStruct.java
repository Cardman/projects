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
        return (byte) value;
    }

}
