package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import code.maths.Rate;


public final class EffectEndRoundFoe extends EffectEndRound {

    private Rate inflictedRateHpTarget;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!inflictedRateHpTarget.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (inflictedRateHpTarget.isZero()) {
            _data.setError(true);
            return;

        }
    }

    @Override
    public RelationType getRelation() {
        return RelationType.ADV;
    }

    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }

    public void setInflictedRateHpTarget(Rate _inflictedRateHpTarget) {
        inflictedRateHpTarget = _inflictedRateHpTarget;
    }
}
