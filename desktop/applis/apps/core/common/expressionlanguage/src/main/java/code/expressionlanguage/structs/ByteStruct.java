package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class ByteStruct extends AbsRelativeNumberStruct {

    private final byte value;

    public ByteStruct(byte _value) {
        value = _value;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getContent().getNbAlias().getAliasByte();
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
        return value;
    }

}
