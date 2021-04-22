package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumParsers;

public abstract class AbsRelativeNumberStruct extends NumberStruct {

    @Override
    public final boolean sameReference(Struct _other) {
        if (!(_other instanceof AbsRelativeNumberStruct)) {
            return false;
        }
        return NumParsers.compareRelative(this,(AbsRelativeNumberStruct)_other);
    }

    @Override
    public long randCode() {
        return longStruct();
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(Long.toString(longStruct()));
    }

    @Override
    public StringStruct getDisplayedString(AnalyzedPageEl _an) {
        return new StringStruct(Long.toString(longStruct()));
    }

    @Override
    public double doubleStruct() {
        return (double)longStruct();
    }

    @Override
    public float floatStruct() {
        return (float)longStruct();
    }
}
