package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class FloatStruct extends NumberStruct {

    private final float value;

    public FloatStruct(float _value) {
        value = _value;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getAliasFloat();
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
