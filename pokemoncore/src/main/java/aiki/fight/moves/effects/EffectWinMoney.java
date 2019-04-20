package aiki.fight.moves.effects;

import aiki.db.DataBase;
import code.maths.Rate;


public final class EffectWinMoney extends Effect {

    private Rate winningRateBySumTargetUser;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (winningRateBySumTargetUser.isZero()) {
            _data.setError(true);
            return;

        }
        if (!winningRateBySumTargetUser.isZeroOrGt()) {
            _data.setError(true);

        }
    }

    public Rate getWinningRateBySumTargetUser() {
        return winningRateBySumTargetUser;
    }

    public void setWinningRateBySumTargetUser(Rate _winningRateBySumTargetUser) {
        winningRateBySumTargetUser = _winningRateBySumTargetUser;
    }
}
