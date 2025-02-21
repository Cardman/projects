package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundFoe;
import code.maths.Rate;
import code.scripts.pages.aiki.*;

public class EffectEndRoundFoeBean extends EffectEndRoundBean {
    private Rate inflictedRateHpTarget;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundFoe effect_ = (EffectEndRoundFoe) getEffect();
        inflictedRateHpTarget = effect_.getInflictedRateHpTarget();
    }

    @Override
    public void buildSub() {
        super.buildSub();
        formatMessage(MessagesPkBean.ENDROUND_FOE, MessagesDataEndroundFoe.M_P_5_EFFECT);
        displayIntDef(inflictedRateHpTarget,MessagesPkBean.ENDROUND_FOE,MessagesDataEndroundFoe.M_P_5_FOE);
    }

    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }
}