package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class EffectClone extends Effect {

    private Rate hpRateClone;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTarget(TargetChoice.LANCEUR,getTargetChoice(),_data);
        DataInfoChecker.checkPositive(hpRateClone,_data);
    }

    public Rate getHpRateClone() {
        return hpRateClone;
    }

    public void setHpRateClone(Rate _hpRateClone) {
        hpRateClone = _hpRateClone;
    }
}
