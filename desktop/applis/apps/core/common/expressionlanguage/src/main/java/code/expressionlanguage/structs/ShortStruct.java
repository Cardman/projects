package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class ShortStruct extends AbsRelativeNumberStruct {

    private final short value;

    public ShortStruct(short _value) {
        value = _value;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getContent().getNbAlias().getAliasShort();
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
        return value;
    }

    @Override
    public byte byteStruct() {
        return (byte) value;
    }

}
