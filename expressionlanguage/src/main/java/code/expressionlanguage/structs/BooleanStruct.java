package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class BooleanStruct implements DisplayableStruct {

    private static final BooleanStruct FALSE = new BooleanStruct();
    private static final BooleanStruct TRUE = new BooleanStruct();

    private BooleanStruct() {
    }

    public static BooleanStruct of(boolean _value) {
        if (_value) {
            return TRUE;
        }
        return FALSE;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getAliasBoolean();
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        if (this == TRUE) {
            return new StringStruct(_an.getStandards().getDisplayedStrings().getTrueString());
        }
        return new StringStruct(_an.getStandards().getDisplayedStrings().getFalseString());
    }

    public StringStruct exportValue() {
        if (this == TRUE) {
            return new StringStruct("1");
        }
        return new StringStruct("0");
    }

    public BooleanStruct neg() {
        if (this == TRUE) {
            return FALSE;
        }
        return TRUE;
    }

    public BooleanStruct and(BooleanStruct _other) {
        if (this == FALSE) {
            return FALSE;
        }
        return _other;
    }

    public BooleanStruct or(BooleanStruct _other) {
        if (this == TRUE) {
            return TRUE;
        }
        return _other;
    }

    public boolean getInstance() {
        return this == TRUE;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

}
