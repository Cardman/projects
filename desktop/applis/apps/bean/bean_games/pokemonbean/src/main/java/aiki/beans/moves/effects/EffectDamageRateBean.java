package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectDamageRate;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesDataEffdamagerate;
import code.scripts.pages.aiki.MessagesPkBean;

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

    @Override
    public void buildSubEff() {
        displayBoolFull(toInt(getWinHp()), MessagesPkBean.EFF_DAMAGERATE, MessagesDataEffdamagerate.M_P_46_POS_RATE,MessagesDataEffdamagerate.M_P_46_NEG_RATE,getRateDamage().toNumberString());
    }

    public boolean getWinHp() {
        return winHp;
    }

    public Rate getRateDamage() {
        return rateDamage;
    }
}