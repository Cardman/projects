package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class EffectFullHpRate extends Effect {

    private Rate leftUserHp;
    private String restoredHp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositiveOrZero(leftUserHp,_data);
        if (!leftUserHp.isZero()) {
            DataInfoChecker.checkEmptyString(restoredHp,_data);
        }
    }

    public Rate getLeftUserHp() {
        return leftUserHp;
    }

    public void setLeftUserHp(Rate _leftUserHp) {
        leftUserHp = _leftUserHp;
    }

    public String getRestoredHp() {
        return restoredHp;
    }

    public void setRestoredHp(String _restoredHp) {
        restoredHp = _restoredHp;
    }

}
