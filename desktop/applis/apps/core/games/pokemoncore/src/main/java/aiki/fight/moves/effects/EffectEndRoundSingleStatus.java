package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.status.StatusType;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.StringMap;


public final class EffectEndRoundSingleStatus extends EffectEndRoundStatus {

    private boolean incrementingDamageByRounds;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getInflictedRateHpTarget().isZeroOrLt()) {
            _data.setError(true);
        }
    }

    @Override
    public RelationType getRelation() {
        return RelationType.STATUT;
    }

    public boolean isIncrementingDamageByRounds() {
        return incrementingDamageByRounds;
    }

    public void setIncrementingDamageByRounds(
            boolean _incrementingDamageByRounds) {
        incrementingDamageByRounds = _incrementingDamageByRounds;
    }
}
