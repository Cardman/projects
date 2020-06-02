package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;
import code.maths.Rate;

public class EffectEndRoundPositionRelationBean extends EffectEndRoundBean {
    private Rate healHp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundPositionRelation effect_ = (EffectEndRoundPositionRelation) getEffect();
        healHp = effect_.getHealHp();
    }

    public Rate getHealHp() {
        return healHp;
    }
}