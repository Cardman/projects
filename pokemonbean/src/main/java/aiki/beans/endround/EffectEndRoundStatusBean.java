package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundStatus;
import code.maths.Rate;

public class EffectEndRoundStatusBean extends EffectEndRoundBean{
    private final String endRoundStatusHtml="web/html/endround/status.html";
    private Rate inflictedRateHpTarget;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundStatus effect_ = (EffectEndRoundStatus) getEffect();
        inflictedRateHpTarget = effect_.getInflictedRateHpTarget();
    }

    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }

    public String getEndRoundStatusHtml() {
        return endRoundStatusHtml;
    }
}