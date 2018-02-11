package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectAlly;
import code.maths.Rate;

public class EffectAllyBean extends EffectBean {
    private Rate multAllyDamage;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectAlly effect_ = (EffectAlly) getEffect();
        multAllyDamage = effect_.getMultAllyDamage();
    }

    public Rate getMultAllyDamage() {
        return multAllyDamage;
    }
}