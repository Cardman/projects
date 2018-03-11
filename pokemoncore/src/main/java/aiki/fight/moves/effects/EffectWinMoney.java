package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.maths.Rate;
import code.util.annot.RwXml;

@RwXml
public final class EffectWinMoney extends Effect {

    private Rate winningRateBySumTargetUser;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (winningRateBySumTargetUser.isZero()) {
            throw new DataException();
        }
        if (!winningRateBySumTargetUser.isZeroOrGt()) {
            throw new DataException();
        }
    }

    public Rate getWinningRateBySumTargetUser() {
        return winningRateBySumTargetUser;
    }

    public void setWinningRateBySumTargetUser(Rate _winningRateBySumTargetUser) {
        winningRateBySumTargetUser = _winningRateBySumTargetUser;
    }
}
