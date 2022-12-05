package aiki.beans;

import aiki.fight.status.effects.EffectPartnerStatus;
import code.bean.nat.NaNuSt;

public final class EffectPartnerStatusStruct extends NaNuSt {
    private final EffectPartnerStatus inst;
    public EffectPartnerStatusStruct(EffectPartnerStatus _effectPartnerStatus) {
        inst=(_effectPartnerStatus);
    }

    public EffectPartnerStatus getEffectPartnerStatus() {
        return inst;
    }
}
