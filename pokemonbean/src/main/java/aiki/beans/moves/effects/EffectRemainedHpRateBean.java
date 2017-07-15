package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.moves.effects.EffectRemainedHpRate;

public class EffectRemainedHpRateBean extends EffectBean {

    @Accessible
    private boolean winHp;

    @Accessible
    private Rate rateHp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectRemainedHpRate effect_ = (EffectRemainedHpRate) getEffect();
        winHp = effect_.getRateHp().isZeroOrGt();
        rateHp = effect_.getRateHp().absNb();
    }
}
