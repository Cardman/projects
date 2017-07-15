package aiki.beans.endround;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.moves.effects.EffectEndRoundFoe;

public class EffectEndRoundFoeBean extends EffectEndRoundBean {

    @Accessible
    private Rate inflictedRateHpTarget;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundFoe effect_ = (EffectEndRoundFoe) getEffect();
        inflictedRateHpTarget = effect_.getInflictedRateHpTarget();
    }
}
