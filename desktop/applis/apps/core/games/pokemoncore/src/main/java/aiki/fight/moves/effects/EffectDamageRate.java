package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class EffectDamageRate extends Effect {

    private Rate rateDamage;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTarget(TargetChoice.LANCEUR,getTargetChoice(),_data);
        DataInfoChecker.checkNonZero(rateDamage,_data);
    }

    public Rate getRateDamage() {
        return rateDamage;
    }

    public void setRateDamage(Rate _rateDamage) {
        rateDamage = _rateDamage;
    }

}
