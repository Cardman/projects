package aiki.beans;

import aiki.fight.status.effects.EffectPartnerStatus;

public final class EffectPartnerStatusStruct extends ParamNatStruct<EffectPartnerStatus> {
    public EffectPartnerStatusStruct(EffectPartnerStatus _effectPartnerStatus) {
        super(_effectPartnerStatus);
    }

    public EffectPartnerStatus getEffectPartnerStatus() {
        return getInstance();
    }
}
