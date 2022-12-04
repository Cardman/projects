package code.bean.nat;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.maths.Rate;

public final class RateStruct extends NaNuSt implements NatDisplayableStruct {

    private final Rate value;
    public RateStruct(Rate _instance) {
        value = _instance;
    }

    public static Rate convertToRate(NaSt _r) {
        if (_r instanceof RateStruct) {
            return ((RateStruct)_r).getInstance();
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
