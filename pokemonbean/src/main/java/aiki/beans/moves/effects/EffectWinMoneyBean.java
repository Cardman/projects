package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.moves.effects.EffectWinMoney;

public class EffectWinMoneyBean extends EffectBean {

    @Accessible
    private Rate winningRateBySumTargetUser;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectWinMoney effect_ = (EffectWinMoney) getEffect();
        winningRateBySumTargetUser = effect_.getWinningRateBySumTargetUser();
    }
}
