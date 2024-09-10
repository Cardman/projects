package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundStatus;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;

public class EffectEndRoundStatusBean extends EffectEndRoundBean{
    static final String END_ROUND_STATUS_HTML= PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_STATUS_HTML;
    private Rate inflictedRateHpTarget;

    protected void beforeDisplayingEffectEndRoundStatus() {
        EffectEndRoundStatus effect_ = (EffectEndRoundStatus) getEffect();
        inflictedRateHpTarget = effect_.getInflictedRateHpTarget();
    }

    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }

}