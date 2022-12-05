package aiki.beans.game;

import aiki.beans.facade.game.dto.StatisticInfoPkPlayer;
import code.bean.nat.NaNuSt;

public final class StatisticInfoPkPlayerStruct extends NaNuSt {
    private final StatisticInfoPkPlayer inst;
    public StatisticInfoPkPlayerStruct(StatisticInfoPkPlayer _instance) {
        inst=(_instance);
    }
    public StatisticInfoPkPlayer getStatisticInfoPkPlayer() {
        return inst;
    }
}
