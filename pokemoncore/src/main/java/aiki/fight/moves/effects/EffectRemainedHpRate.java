package aiki.fight.moves.effects;

import aiki.DataBase;
import code.maths.Rate;
import code.util.annot.RwXml;

@RwXml
public final class EffectRemainedHpRate extends Effect {

    private Rate rateHp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (rateHp.isZero()) {
            _data.setError(true);
            return;

        }
    }

    public Rate getRateHp() {
        return rateHp;
    }

    public void setRateHp(Rate _rateHp) {
        rateHp = _rateHp;
    }

}
