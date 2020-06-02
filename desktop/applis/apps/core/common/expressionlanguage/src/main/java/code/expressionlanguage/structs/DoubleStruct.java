package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class DoubleStruct extends NumberStruct {

    private final double value;

    public DoubleStruct(double _value) {
        value = _value;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getAliasDouble();
    }

    @Override
    public double doubleStruct() {
        return value;
    }

    @Override
    public float floatStruct() {
        return (float) value;
    }

    @Override
    public long longStruct() {
        return (long) value;
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
