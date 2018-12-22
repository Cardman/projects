package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import code.maths.Rate;


public final class EffectEndRoundStatusRelation extends EffectEndRoundStatus {

    private Rate thievedHpRateTargetToUser;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!getInflictedRateHpTarget().isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (!thievedHpRateTargetToUser.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (getInflictedRateHpTarget().isZero()) {
            if (!thievedHpRateTargetToUser.isZero()) {
                return;
            }
            _data.setError(true);
            return;

        }
        if (!thievedHpRateTargetToUser.isZero()) {
            _data.setError(true);
            return;

        }
        return;
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
