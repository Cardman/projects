package code.bean.nat;

import code.maths.Rate;

public final class RtSt extends NaNuSt implements NatDisplayableStruct {

    private final Rate value;
    public RtSt(Rate _instance) {
        value = _instance;
    }

    public static Rate convertToRate(NaSt _r) {
        if (_r instanceof RtSt) {
            return ((RtSt)_r).getInstance();
        }
        return Rate.zero();
    }

    public Rate getInstance() {
        return value;
    }

    @Override
    public NaStSt getDisplayedString() {
        return new NaStSt(getInstance().toNumberString());
    }
}
