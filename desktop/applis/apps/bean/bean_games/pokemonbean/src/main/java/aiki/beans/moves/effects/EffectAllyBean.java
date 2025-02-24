package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectAlly;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesDataEffally;
import code.scripts.pages.aiki.MessagesPkBean;

public class EffectAllyBean extends EffectBean {
    private Rate multAllyDamage;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectAlly effect_ = (EffectAlly) getEffect();
        multAllyDamage = effect_.getMultAllyDamage();
    }

    @Override
    public void buildSubEff() {
        formatMessage(MessagesPkBean.EFF_ALLY, MessagesDataEffally.M_P_38_MUL_ALLY_DAMAGE,getMultAllyDamage().toNumberString());
    }

    public Rate getMultAllyDamage() {
        return multAllyDamage;
    }
}