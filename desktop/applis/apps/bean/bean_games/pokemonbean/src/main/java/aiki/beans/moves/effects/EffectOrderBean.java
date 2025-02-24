package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectOrder;
import code.scripts.pages.aiki.MessagesDataEfforder;
import code.scripts.pages.aiki.MessagesPkBean;

public class EffectOrderBean extends EffectBean {
    private boolean targetAttacksLast;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectOrder effect_ = (EffectOrder) getEffect();
        targetAttacksLast = effect_.getTargetAttacksLast();
    }

    @Override
    public void buildSubEff() {
        displayBoolFull(toInt(getTargetAttacksLast()), MessagesPkBean.EFF_ORDER, MessagesDataEfforder.M_P_53_LAST,MessagesDataEfforder.M_P_53_AFTER_USER);
    }

    public boolean getTargetAttacksLast() {
        return targetAttacksLast;
    }
}