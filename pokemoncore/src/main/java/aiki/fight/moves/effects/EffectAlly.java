package aiki.fight.moves.effects;

import aiki.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.util.annot.RwXml;

@RwXml
public final class EffectAlly extends Effect {

    private Rate multAllyDamage;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.ALLIE) {
            if (getTargetChoice() != TargetChoice.ALLIES) {
                _data.setError(true);
                return;

            }
        }
        if (!multAllyDamage.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
    }

    public Rate getMultAllyDamage() {
        return multAllyDamage;
    }

    public void setMultAllyDamage(Rate _multAllyDamage) {
        multAllyDamage = _multAllyDamage;
    }
}
