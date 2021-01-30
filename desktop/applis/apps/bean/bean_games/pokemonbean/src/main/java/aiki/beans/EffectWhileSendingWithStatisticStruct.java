package aiki.beans;

import aiki.fight.effects.EffectWhileSendingWithStatistic;

public final class EffectWhileSendingWithStatisticStruct extends ParamNatStruct<EffectWhileSendingWithStatistic> {
    public EffectWhileSendingWithStatisticStruct(EffectWhileSendingWithStatistic _effectPartnerStatus, String _className) {
        super(_effectPartnerStatus,_className);
    }

    public EffectWhileSendingWithStatistic getEffectPartnerStatus() {
        return getInstance();
    }
}
