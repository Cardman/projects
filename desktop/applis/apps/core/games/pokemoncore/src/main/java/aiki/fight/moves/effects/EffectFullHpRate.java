package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class EffectFullHpRate extends Effect {

    private Rate leftUserHp;
    private String restoredHp;
    private Rate closestFoeDamageRateHp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositiveOrZero(leftUserHp,_data);
        DataInfoChecker.checkPositiveOrZero(closestFoeDamageRateHp,_data);
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

    public Rate getClosestFoeDamageRateHp() {
        return closestFoeDamageRateHp;
    }

    public void setClosestFoeDamageRateHp(Rate _closestFoeDamageRateHp) {
        closestFoeDamageRateHp = _closestFoeDamageRateHp;
    }
}
