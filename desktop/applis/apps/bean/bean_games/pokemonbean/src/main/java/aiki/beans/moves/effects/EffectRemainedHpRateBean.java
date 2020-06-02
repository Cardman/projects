package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectRemainedHpRate;
import code.maths.Rate;

public class EffectRemainedHpRateBean extends EffectBean {
    private boolean winHp;
    private Rate rateHp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectRemainedHpRate effect_ = (EffectRemainedHpRate) getEffect();
        winHp = effect_.getRateHp().isZeroOrGt();
        rateHp = effect_.getRateHp().absNb();
    }

    public boolean getWinHp() {
        return winHp;
    }

    public Rate getRateHp() {
        return rateHp;
    }
}