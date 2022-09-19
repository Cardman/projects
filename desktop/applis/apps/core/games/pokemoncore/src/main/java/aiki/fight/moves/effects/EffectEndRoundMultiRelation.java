package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.status.StatusType;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.StringMap;


public final class EffectEndRoundMultiRelation extends EffectEndRound {

    private StringMap<Rate> damageByStatus;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkStringListContains(DataInfoChecker.filterStatusExclude(_data,StatusType.RELATION_UNIQUE).getKeys(),damageByStatus.getKeys(),_data);
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
