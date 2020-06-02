package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;


public final class EffectDamageRate extends Effect {

    private Rate rateDamage;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            _data.setError(true);
        }
        if (rateDamage.isZero()) {
            _data.setError(true);
        }
    }

    public Rate getRateDamage() {
        return rateDamage;
    }

    public void setRateDamage(Rate _rateDamage) {
        rateDamage = _rateDamage;
    }

}
