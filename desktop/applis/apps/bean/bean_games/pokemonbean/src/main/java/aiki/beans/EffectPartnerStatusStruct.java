package aiki.beans;

import aiki.fight.status.effects.EffectPartnerStatus;

public final class EffectPartnerStatusStruct extends ParamNatStruct<EffectPartnerStatus> {
    public EffectPartnerStatusStruct(EffectPartnerStatus _effectPartnerStatus,String _className) {
        super(_effectPartnerStatus,_className);
    }

    public EffectPartnerStatus getEffectPartnerStatus() {
        return getInstance();
    }
}
