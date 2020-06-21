package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.stds.*;

public abstract class NumberStruct extends WithoutParentStruct implements DisplayableStruct {

    public abstract double doubleStruct();
    public abstract float floatStruct();
    public abstract long longStruct();
    public abstract int intStruct();
    public abstract short shortStruct();
    public abstract byte byteStruct();

    @Override
    public final boolean sameReference(Struct _other) {
        if (!(_other instanceof NumberStruct)) {
            return false;
        }
        return NumParsers.sameReference(this, (NumberStruct) _other);
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        DisplayedStrings dis_ = _an.getStandards().getDisplayedStrings();
        return NumParsers.getStringValue(this,dis_.getInfinity(),
                dis_.getNan(),
                dis_.getExponent());
    }

}
