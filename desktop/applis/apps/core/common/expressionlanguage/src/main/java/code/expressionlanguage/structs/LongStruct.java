package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class LongStruct extends AbsRelativeNumberStruct {

    private final long value;

    public LongStruct(long _value) {
        value = _value;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getContent().getNbAlias().getAliasLong();
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
