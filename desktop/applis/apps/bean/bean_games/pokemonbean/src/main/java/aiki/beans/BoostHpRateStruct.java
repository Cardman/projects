package aiki.beans;

import aiki.fight.util.BoostHpRate;
import code.bean.nat.NaNuSt;

public final class BoostHpRateStruct extends NaNuSt {
    private final BoostHpRate inst;
    public BoostHpRateStruct(BoostHpRate _instance) {
        inst=(_instance);
    }
    public BoostHpRate getBoostHpRate(){
        return inst;
    }
}
