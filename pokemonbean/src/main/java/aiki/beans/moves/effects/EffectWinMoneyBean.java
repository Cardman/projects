package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectWinMoney;
import code.maths.Rate;

public class EffectWinMoneyBean extends EffectBean {
    private Rate winningRateBySumTargetUser;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectWinMoney effect_ = (EffectWinMoney) getEffect();
        winningRateBySumTargetUser = effect_.getWinningRateBySumTargetUser();
    }

    public Rate getWinningRateBySumTargetUser() {
        return winningRateBySumTargetUser;
    }
}