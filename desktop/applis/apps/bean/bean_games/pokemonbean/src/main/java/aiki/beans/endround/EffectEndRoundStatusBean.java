package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundStatus;
import code.maths.Rate;

public class EffectEndRoundStatusBean extends EffectEndRoundBean{
    static final String END_ROUND_STATUS_HTML="web/html/endround/status.html";
    private Rate inflictedRateHpTarget;

    protected void beforeDisplayingEffectEndRoundStatus() {
        EffectEndRoundStatus effect_ = (EffectEndRoundStatus) getEffect();
        inflictedRateHpTarget = effect_.getInflictedRateHpTarget();
    }

    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }

}