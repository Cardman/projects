package code.bean.nat;

import code.expressionlanguage.structs.AbNullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.maths.LgInt;

public final class LgIntStruct extends AbNullStruct implements NatDisplayableStruct {

    private final LgInt value;
    public LgIntStruct(LgInt _instance) {
        value = _instance;
    }

    public LgInt getInstance() {
        return value;
    }

    @Override
    public StringStruct getDisplayedString() {
        return new StringStruct(getInstance().toNumberString());
    }
}
