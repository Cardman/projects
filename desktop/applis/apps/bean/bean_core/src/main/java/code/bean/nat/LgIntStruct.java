package code.bean.nat;

import code.bean.nat.*;
import code.bean.nat.*;
import code.maths.LgInt;

public final class LgIntStruct extends NaNuSt implements NatDisplayableStruct {

    private final LgInt value;
    public LgIntStruct(LgInt _instance) {
        value = _instance;
    }

    public LgInt getInstance() {
        return value;
    }

    @Override
    public NaStSt getDisplayedString() {
        return new NaStSt(getInstance().toNumberString());
    }
}
