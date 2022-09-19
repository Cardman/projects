package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.util.DataInfoChecker;


public final class EffectEndRoundSingleStatus extends EffectEndRoundStatus {

    private boolean incrementingDamageByRounds;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositiveOrZero(getInflictedRateHpTarget(),_data);
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
