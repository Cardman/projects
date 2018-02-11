package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundFoe;
import code.maths.Rate;

public class EffectEndRoundFoeBean extends EffectEndRoundBean {
    private Rate inflictedRateHpTarget;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundFoe effect_ = (EffectEndRoundFoe) getEffect();
        inflictedRateHpTarget = effect_.getInflictedRateHpTarget();
    }

    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }
}