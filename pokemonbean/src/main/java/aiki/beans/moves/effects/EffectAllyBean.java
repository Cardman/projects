package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.moves.effects.EffectAlly;

public class EffectAllyBean extends EffectBean {

    @Accessible
    private Rate multAllyDamage;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectAlly effect_ = (EffectAlly) getEffect();
        multAllyDamage = effect_.getMultAllyDamage();
    }
}
