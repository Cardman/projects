package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class IntStruct extends AbsRelativeNumberStruct {

    private final int value;

    public IntStruct(int _value) {
        value = _value;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getContent().getNbAlias().getAliasInteger();
    }

    @Override
    public long longStruct() {
        return value;
    }

    @Override
    public int intStruct() {
        return value;
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
