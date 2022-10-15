package aiki.beans;

import aiki.fight.effects.EffectWhileSendingWithStatistic;

public final class EffectWhileSendingWithStatisticStruct extends ParamNatStruct<EffectWhileSendingWithStatistic> {
    public EffectWhileSendingWithStatisticStruct(EffectWhileSendingWithStatistic _effectPartnerStatus) {
        super(_effectPartnerStatus);
    }

    public EffectWhileSendingWithStatistic getEffectPartnerStatus() {
        return getInstance();
    }
}
