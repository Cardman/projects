package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundStatusRelation;
import code.maths.Rate;
import code.scripts.pages.aiki.*;

public class EffectEndRoundStatusRelationBean extends EffectEndRoundStatusBean {
    private Rate thievedHpRateTargetToUser;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        beforeDisplayingEffectEndRoundStatus();
        EffectEndRoundStatusRelation effect_ = (EffectEndRoundStatusRelation) getEffect();
        thievedHpRateTargetToUser = effect_.getThievedHpRateTargetToUser();
    }

    @Override
    public void buildSub() {
        super.buildSub();
        formatMessage(MessagesPkBean.ENDROUND_STATUSRELATION,MessagesDataEndroundStatusrelation.M_P_12_EFFECT);
        displayIntDef(thievedHpRateTargetToUser,MessagesPkBean.ENDROUND_STATUSRELATION,MessagesDataEndroundStatusrelation.M_P_12_THIEVED_HP_RATE);
    }
    public Rate getThievedHpRateTargetToUser() {
        return thievedHpRateTargetToUser;
    }
}