package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectDamageRate;
import code.maths.Rate;

public class EffectDamageRateBean extends EffectBean {
    private boolean winHp;
    private Rate rateDamage;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectDamageRate effect_ = (EffectDamageRate) getEffect();
        winHp = effect_.getRateDamage().isZeroOrGt();
        rateDamage = effect_.getRateDamage().absNb();
    }

    public boolean getWinHp() {
        return winHp;
    }

    public Rate getRateDamage() {
        return rateDamage;
    }
}