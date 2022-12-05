package aiki.beans;

import aiki.fight.effects.EffectWhileSendingWithStatistic;
import code.bean.nat.NaNuSt;

public final class EffectWhileSendingWithStatisticStruct extends NaNuSt {
    private final EffectWhileSendingWithStatistic inst;
    public EffectWhileSendingWithStatisticStruct(EffectWhileSendingWithStatistic _effectPartnerStatus) {
        inst=(_effectPartnerStatus);
    }

    public EffectWhileSendingWithStatistic getEffectPartnerStatus() {
        return inst;
    }
}
