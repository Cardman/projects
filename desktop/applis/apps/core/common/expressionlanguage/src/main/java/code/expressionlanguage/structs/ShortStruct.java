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


}
