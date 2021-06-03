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


}
