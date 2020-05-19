package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class LongStruct extends NumberStruct {

    private final long value;

    public LongStruct(long _value) {
        value = _value;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getAliasLong();
    }

    @Override
    public double doubleStruct() {
        return value;
    }

    @Override
    public float floatStruct() {
        return value;
    }

    @Override
    public long longStruct() {
        return value;
    }

    @Override
    public int intStruct() {
        return (int) value;
    }

    @Override
    public short shortStruct() {
        return (short) value;
    }

    @Override
    public byte byteStruct() {
        return (byte) value;
    }

}
