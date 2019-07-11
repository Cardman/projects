package aiki.fight.moves.effects;

import aiki.db.DataBase;
import code.maths.Rate;


public final class EffectRemainedHpRate extends Effect {

    private Rate rateHp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (rateHp.isZero()) {
            _data.setError(true);
        }
    }

    public Rate getRateHp() {
        return rateHp;
    }

    public void setRateHp(Rate _rateHp) {
        rateHp = _rateHp;
    }

}
