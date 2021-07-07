package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.DisplayedStrings;

public abstract class AbsRealNumberStruct extends NumberStruct {
    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof AbsRealNumberStruct)) {
            return false;
        }
        return NumParsers.compareFloat(this, (AbsRealNumberStruct) _other);
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(doubleStruct());
    }
    @Override
    public long longStruct() {
        return (long) doubleStruct();
    }

    @Override
    public int intStruct() {
        return (int) doubleStruct();
    }

    @Override
    public short shortStruct() {
        return (short) doubleStruct();
    }

    @Override
    public byte byteStruct() {
        return (byte) doubleStruct();
    }

    @Override
    public float floatStruct() {
        return (float) doubleStruct();
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        DisplayedStrings dis_ = _an.getStandards().getDisplayedStrings();
        return getStringValue(dis_);
    }

    @Override
    public StringStruct getDisplayedString(AnalyzedPageEl _an) {
        DisplayedStrings dis_ = _an.getDisplayedStrings();
        return getStringValue(dis_);
    }

    protected abstract StringStruct getStringValue(DisplayedStrings _dis);
}
