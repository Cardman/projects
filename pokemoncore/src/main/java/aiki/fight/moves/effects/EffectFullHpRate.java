package aiki.fight.moves.effects;

import aiki.db.DataBase;
import code.maths.Rate;


public final class EffectFullHpRate extends Effect {

    private Rate leftUserHp;
    private String restoredHp;
    private Rate closestFoeDamageRateHp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!leftUserHp.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (!closestFoeDamageRateHp.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (!leftUserHp.isZero()) {
            if (!restoredHp.isEmpty()) {
                _data.setError(true);
                return;

            }
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
