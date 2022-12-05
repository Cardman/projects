package aiki.beans.fight;

import aiki.beans.facade.fight.StatisticInfo;
import code.bean.nat.NaNuSt;

public final class StatisticInfoStruct extends NaNuSt {
    private final StatisticInfo inst;
    public StatisticInfoStruct(StatisticInfo _instance) {
        inst=(_instance);
    }
    public StatisticInfo getStatisticInfo() {
        return inst;
    }
}
