package aiki.beans;

import aiki.fight.util.EfficiencyRate;
import code.bean.nat.NaNuSt;

public final class EfficiencyRateStruct extends NaNuSt {
    private final EfficiencyRate inst;
    public EfficiencyRateStruct(EfficiencyRate _instance) {
        inst=(_instance);
    }
    public EfficiencyRate getEfficiencyRate() {
        return inst;
    }
}
