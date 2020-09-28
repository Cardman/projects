package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.stds.DisplayedStrings;
import code.expressionlanguage.stds.LgNames;

public final class BooleanStruct extends WithoutParentIdStruct implements DisplayableStruct,AnaDisplayableStruct {

    private static final BooleanStruct FALSE = new BooleanStruct();
    private static final BooleanStruct TRUE = new BooleanStruct();
    private static final boolean ALT_VALUE = true;

    private BooleanStruct() {
    }

    public static BooleanStruct of(boolean _value) {
        if (_value == ALT_VALUE) {
            return TRUE;
        }
        return FALSE;
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getAliasBoolean();
    }

    @Override
    public StringStruct getDisplayedString(AnalyzedPageEl _an) {
        return getDisplayedString(_an.getDisplayedStrings());
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        LgNames stds_ = _an.getStandards();
        return getDisplayedString(stds_.getDisplayedStrings());
    }

    private StringStruct getDisplayedString(DisplayedStrings _displayedStrings) {
        if (this == TRUE) {
            return new StringStruct(_displayedStrings.getTrueString());
        }
        return new StringStruct(_displayedStrings.getFalseString());
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

    public static boolean isTrue(Struct _other) {
        return _other == TRUE;
    }

    public static boolean isFalse(Struct _other) {
        return _other == FALSE;
    }


}
