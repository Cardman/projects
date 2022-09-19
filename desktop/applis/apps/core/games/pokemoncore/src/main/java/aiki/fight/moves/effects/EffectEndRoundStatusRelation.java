package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class EffectEndRoundStatusRelation extends EffectEndRoundStatus {

    private Rate thievedHpRateTargetToUser;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositiveOrZero(getInflictedRateHpTarget(),_data);
        DataInfoChecker.checkPositiveOrZero(thievedHpRateTargetToUser,_data);
        if (getInflictedRateHpTarget().isZero()) {
            if (!thievedHpRateTargetToUser.isZero()) {
                return;
            }
            _data.setError(true);
        }
        DataInfoChecker.checkZero(thievedHpRateTargetToUser,_data);
    }

    @Override
    public RelationType getRelation() {
        return RelationType.STATUT_RELATION;
    }

    public Rate getThievedHpRateTargetToUser() {
        return thievedHpRateTargetToUser;
    }

    public void setThievedHpRateTargetToUser(Rate _thievedHpRateTargetToUser) {
        thievedHpRateTargetToUser = _thievedHpRateTargetToUser;
    }
}
