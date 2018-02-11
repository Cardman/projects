package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectOrder;

public class EffectOrderBean extends EffectBean {
    private boolean targetAttacksLast;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectOrder effect_ = (EffectOrder) getEffect();
        targetAttacksLast = effect_.getTargetAttacksLast();
    }

    public boolean getTargetAttacksLast() {
        return targetAttacksLast;
    }
}