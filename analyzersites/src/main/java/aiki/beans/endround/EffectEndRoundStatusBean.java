package aiki.beans.endround;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.moves.effects.EffectEndRoundStatus;

public class EffectEndRoundStatusBean extends EffectEndRoundBean{

    @Accessible
    private final String endRoundStatusHtml="web/html/endround/status.html";

    @Accessible
    private Rate inflictedRateHpTarget;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundStatus effect_ = (EffectEndRoundStatus) getEffect();
        inflictedRateHpTarget = effect_.getInflictedRateHpTarget();
    }
}
