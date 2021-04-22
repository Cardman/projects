package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumParsers;

public final class RangeStruct extends WithoutParentStruct implements DisplayableStruct,AnaDisplayableStruct {
    private final int lower;
    private final int upper;
    private final int step;

    public RangeStruct(int _lower) {
        this.lower = _lower;
        this.upper = -1;
        this.step = 1;
    }

    public RangeStruct(int _lower, int _upper) {
        this.lower = _lower;
        this.upper = _upper;
        this.step = 1;
    }

    public RangeStruct(int _lower, int _upper, int _step) {
        this.lower = _lower;
        this.upper = _upper;
        this.step = _step;
    }

    @Override
    public StringStruct getDisplayedString(AnalyzedPageEl _an) {
        if (step == 1) {
            if (isUnlimited()) {
                return new StringStruct(lower+"");
            }
            return new StringStruct(lower+";"+upper);
        }
        if (isUnlimited()) {
            return new StringStruct(lower+";;"+step);
        }
        return new StringStruct(lower+";"+upper+";"+step);
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        if (step == 1) {
            if (isUnlimited()) {
                return new StringStruct(lower+"");
            }
            return new StringStruct(lower+";"+upper);
        }
        if (isUnlimited()) {
            return new StringStruct(lower+";;"+step);
        }
        return new StringStruct(lower+";"+upper+";"+step);
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
                && upper == ((RangeStruct)_other).upper
                && step == ((RangeStruct)_other).step;
    }

    @Override
    public long randCode() {
        long r_ = NumParsers.mergeRandCode(1,NumParsers.randCode(lower));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(upper));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(step));
        return r_;
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

    public int getStep() {
        return step;
    }
}
