package aiki.fight.moves.effects;

import aiki.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.status.StatusType;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.StringMap;


public final class EffectEndRoundMultiRelation extends EffectEndRound {

    private StringMap<Rate> damageByStatus;
    private StringMap<Rate> multDamageStatus;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (EntryCust<String, Rate> e : damageByStatus.entryList()) {
            if (!_data.getStatus().contains(e.getKey())) {
                _data.setError(true);
                return;

            }
            if (_data.getStatus(e.getKey()).getStatusType() == StatusType.RELATION_UNIQUE) {
                _data.setError(true);
                return;

            }
            e.getValue().isZero();
        }
        for (EntryCust<String, Rate> e : multDamageStatus.entryList()) {
            if (!_data.getStatus().contains(e.getKey())) {
                _data.setError(true);
                return;

            }
            if (_data.getStatus(e.getKey()).getStatusType() == StatusType.RELATION_UNIQUE) {
                _data.setError(true);
                return;

            }
            e.getValue().isZero();
        }
    }

    @Override
    public RelationType getRelation() {
        return RelationType.RELATION_MULTIPLE;
    }

    public StringMap<Rate> getDamageByStatus() {
        return damageByStatus;
    }

    public void setDamageByStatus(StringMap<Rate> _damageByStatus) {
        damageByStatus = _damageByStatus;
    }

    public StringMap<Rate> getMultDamageStatus() {
        return multDamageStatus;
    }

    public void setMultDamageStatus(StringMap<Rate> _multDamageStatus) {
        multDamageStatus = _multDamageStatus;
    }
}
