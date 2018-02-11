package aiki.beans.endround;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;

public class EffectEndRoundPositionRelationBean extends EffectEndRoundBean {

    @Accessible
    private Rate healHp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundPositionRelation effect_ = (EffectEndRoundPositionRelation) getEffect();
        healHp = effect_.getHealHp();
    }
}
