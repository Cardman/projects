package aiki.fight.moves.effects;

import code.maths.Rate;


public abstract class EffectEndRoundStatus extends EffectEndRound {

    private Rate inflictedRateHpTarget;


    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }

    public void setInflictedRateHpTarget(Rate _inflictedRateHpTarget) {
        inflictedRateHpTarget = _inflictedRateHpTarget;
    }
}
