package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class EffectWinMoney extends Effect {

    private Rate winningRateBySumTargetUser;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositive(winningRateBySumTargetUser,_data);
    }

    public Rate getWinningRateBySumTargetUser() {
        return winningRateBySumTargetUser;
    }

    public void setWinningRateBySumTargetUser(Rate _winningRateBySumTargetUser) {
        winningRateBySumTargetUser = _winningRateBySumTargetUser;
    }
}
