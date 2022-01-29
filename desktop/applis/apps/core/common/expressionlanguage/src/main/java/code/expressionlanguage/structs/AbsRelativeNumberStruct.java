package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumParsers;
import code.maths.litteralcom.MathExpUtil;

public abstract class AbsRelativeNumberStruct extends NumberStruct {

    @Override
    public final boolean sameReference(Struct _other) {
        if (!(_other instanceof AbsRelativeNumberStruct)) {
            return false;
        }
        return NumParsers.compareRelative(this,(AbsRelativeNumberStruct)_other);
    }

    @Override
    public int intStruct() {
        return (int)longStruct();
    }

    @Override
    public short shortStruct() {
        return (short)longStruct();
    }

    @Override
    public byte byteStruct() {
        return (byte)longStruct();
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
        return MathExpUtil.toDouble(longStruct());
    }

    @Override
    public float floatStruct() {
        return (float)MathExpUtil.toDouble(longStruct());
    }
}
