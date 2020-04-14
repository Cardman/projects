package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;

public final class BooleanStruct implements DisplayableStruct, ExportableStringStruct {

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
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasBoolean();
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        if (this == TRUE) {
            return new StringStruct(_an.getStandards().getTrueString());
        }
        return new StringStruct(_an.getStandards().getFalseString());
    }
    @Override
    public StringStruct exportValue() {
        if (this == TRUE) {
            return new StringStruct("1");
        }
        return new StringStruct("0");
    }

    public boolean getInstance() {
        return this == TRUE;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

}
