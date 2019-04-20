package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.status.StatusType;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.StringMap;


public final class EffectEndRoundSingleStatus extends EffectEndRoundStatus {

    private StringMap<Rate> multDamageStatus;

    private boolean incrementingDamageByRounds;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (EntryCust<String, Rate> e : multDamageStatus.entryList()) {
            if (!_data.getStatus().contains(e.getKey())) {
                _data.setError(true);
                return;

            }
            if (_data.getStatus(e.getKey()).getStatusType() == StatusType.RELATION_UNIQUE) {
                _data.setError(true);
                return;

            }
        }
        if (!getInflictedRateHpTarget().isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (multDamageStatus.isEmpty()) {
            if (getInflictedRateHpTarget().isZero()) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (!getInflictedRateHpTarget().isZero()) {
            _data.setError(true);

        }
    }

    @Override
    public RelationType getRelation() {
        return RelationType.STATUT;
    }

    public StringMap<Rate> getMultDamageStatus() {
        return multDamageStatus;
    }

    public void setMultDamageStatus(StringMap<Rate> _multDamageStatus) {
        multDamageStatus = _multDamageStatus;
    }

    public boolean isIncrementingDamageByRounds() {
        return incrementingDamageByRounds;
    }

    public void setIncrementingDamageByRounds(
            boolean _incrementingDamageByRounds) {
        incrementingDamageByRounds = _incrementingDamageByRounds;
    }
}
