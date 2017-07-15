package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.moves.effects.EffectDamageRate;

public class EffectDamageRateBean extends EffectBean {

    @Accessible
    private boolean winHp;

    @Accessible
    private Rate rateDamage;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectDamageRate effect_ = (EffectDamageRate) getEffect();
        winHp = effect_.getRateDamage().isZeroOrGt();
        rateDamage = effect_.getRateDamage().absNb();
    }
}
