package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;


public final class EffectClone extends Effect {

    private Rate hpRateClone;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            _data.setError(true);
        }
        if (hpRateClone.isZero()) {
            _data.setError(true);
        }
        if (!hpRateClone.isZeroOrGt()) {
            _data.setError(true);

        }
    }

    public Rate getHpRateClone() {
        return hpRateClone;
    }

    public void setHpRateClone(Rate _hpRateClone) {
        hpRateClone = _hpRateClone;
    }
}
