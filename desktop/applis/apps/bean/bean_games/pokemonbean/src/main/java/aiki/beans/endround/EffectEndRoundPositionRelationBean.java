package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;
import code.maths.Rate;
import code.scripts.pages.aiki.*;

public class EffectEndRoundPositionRelationBean extends EffectEndRoundBean {
    private Rate healHp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundPositionRelation effect_ = (EffectEndRoundPositionRelation) getEffect();
        healHp = effect_.getHealHp();
    }

    @Override
    public void buildSub() {
        super.buildSub();
        formatMessage(MessagesPkBean.ENDROUND_POSITIONRELATION,MessagesDataEndroundPositionrelation.M_P_8_EFFECT);
        displayIntDef(healHp,MessagesPkBean.ENDROUND_POSITIONRELATION,MessagesDataEndroundPositionrelation.M_P_8_HEAL_HP);
    }
    public Rate getHealHp() {
        return healHp;
    }
}