package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.status.StatusType;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.StringMap;


public final class EffectEndRoundMultiRelation extends EffectEndRound {

    private StringMap<Rate> damageByStatus;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (EntryCust<String, Rate> e : damageByStatus.entryList()) {
            if (!_data.getStatus().contains(e.getKey())) {
                _data.setError(true);
                continue;
            }
            if (_data.getStatus(e.getKey()).getStatusType() == StatusType.RELATION_UNIQUE) {
                _data.setError(true);
            }
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

}
