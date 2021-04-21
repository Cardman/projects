package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;

public final class RangeStruct extends WithoutParentStruct implements DisplayableStruct,AnaDisplayableStruct {
    private final int lower;
    private final int upper;

    public RangeStruct(int _lower) {
        this.lower = _lower;
        this.upper = -1;
    }

    public RangeStruct(int _lower, int _upper) {
        this.lower = _lower;
        this.upper = _upper;
    }

    @Override
    public StringStruct getDisplayedString(AnalyzedPageEl _an) {
        if (isUnlimited()) {
            return new StringStruct(lower+"");
        }
        return new StringStruct(lower+";"+upper);
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        if (isUnlimited()) {
            return new StringStruct(lower+"");
        }
        return new StringStruct(lower+";"+upper);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getCoreNames().getAliasRange();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof RangeStruct)) {
            return false;
        }
        return lower == ((RangeStruct)_other).lower
                && upper == ((RangeStruct)_other).upper;
    }

    public boolean isUnlimited() {
        return getUpper() == -1;
    }

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }
}
