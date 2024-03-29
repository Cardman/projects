package code.bean.nat;

import code.maths.LgInt;

public final class LgSt extends NaNuSt implements NatDisplayableStruct {

    private final LgInt value;
    public LgSt(LgInt _instance) {
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
