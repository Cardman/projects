package aiki.beans.moves.effects;
import code.bean.Accessible;
import aiki.fight.moves.effects.EffectOrder;

public class EffectOrderBean extends EffectBean {

    @Accessible
    private boolean targetAttacksLast;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectOrder effect_ = (EffectOrder) getEffect();
        targetAttacksLast = effect_.getTargetAttacksLast();
    }
}
