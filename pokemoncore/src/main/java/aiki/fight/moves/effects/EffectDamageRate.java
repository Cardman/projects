package aiki.fight.moves.effects;

import aiki.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.util.annot.RwXml;

@RwXml
public final class EffectDamageRate extends Effect {

    private Rate rateDamage;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            _data.setError(true);
            return;

        }
        if (rateDamage.isZero()) {
            _data.setError(true);
            return;

        }
    }

    public Rate getRateDamage() {
        return rateDamage;
    }

    public void setRateDamage(Rate _rateDamage) {
        rateDamage = _rateDamage;
    }

}
