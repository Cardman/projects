package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectRemainedHpRate;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesDataEffremainedhprate;
import code.scripts.pages.aiki.MessagesPkBean;

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

    @Override
    public void buildSubEff() {
        displayBoolFull(toInt(getWinHp()), MessagesPkBean.EFF_REMAINEDHPRATE, MessagesDataEffremainedhprate.M_P_56_RATE_WIN,MessagesDataEffremainedhprate.M_P_56_RATE_LOOSE,getRateHp().toNumberString());
    }

    public boolean getWinHp() {
        return winHp;
    }

    public Rate getRateHp() {
        return rateHp;
    }
}