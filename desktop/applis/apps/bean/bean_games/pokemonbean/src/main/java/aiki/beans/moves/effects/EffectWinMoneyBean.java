package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectWinMoney;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesDataEffwinmoney;
import code.scripts.pages.aiki.MessagesPkBean;

public class EffectWinMoneyBean extends EffectBean {
    private Rate winningRateBySumTargetUser;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectWinMoney effect_ = (EffectWinMoney) getEffect();
        winningRateBySumTargetUser = effect_.getWinningRateBySumTargetUser();
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_WINMONEY,MessagesDataEffwinmoney.M_P_70_EFFECT);
    }

    @Override
    public void buildSubEff() {
        formatMessage(MessagesPkBean.EFF_WINMONEY, MessagesDataEffwinmoney.M_P_70_WIN_MONEY,getWinningRateBySumTargetUser().toNumberString());
    }

    public Rate getWinningRateBySumTargetUser() {
        return winningRateBySumTargetUser;
    }
}