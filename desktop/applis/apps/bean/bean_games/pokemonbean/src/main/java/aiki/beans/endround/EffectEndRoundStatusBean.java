package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundStatus;
import code.maths.Rate;
import code.scripts.pages.aiki.*;

public class EffectEndRoundStatusBean extends EffectEndRoundBean{
//    static final String END_ROUND_STATUS_HTML= PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_STATUS_HTML;
    private Rate inflictedRateHpTarget;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        beforeDisplayingEffectEndRoundStatus();
    }

    protected void beforeDisplayingEffectEndRoundStatus() {
        EffectEndRoundStatus effect_ = (EffectEndRoundStatus) getEffect();
        inflictedRateHpTarget = effect_.getInflictedRateHpTarget();
    }

    @Override
    public void buildSub() {
        super.buildSub();
        formatMessage(MessagesPkBean.ENDROUND_STATUS, MessagesDataEndroundStatus.M_P_11_EFFECT);
        displayIntDef(inflictedRateHpTarget,MessagesPkBean.ENDROUND_STATUS,MessagesDataEndroundStatus.M_P_11_INFLICTED_RATE_HP_TARGET);
    }

    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }

}