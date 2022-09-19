package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class EffectAlly extends Effect {

    private Rate multAllyDamage;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTargets(TargetChoice.ALLIE,TargetChoice.ALLIES,getTargetChoice(),_data);
        DataInfoChecker.checkPositiveOrZero(multAllyDamage,_data);
    }

    public Rate getMultAllyDamage() {
        return multAllyDamage;
    }

    public void setMultAllyDamage(Rate _multAllyDamage) {
        multAllyDamage = _multAllyDamage;
    }
}
