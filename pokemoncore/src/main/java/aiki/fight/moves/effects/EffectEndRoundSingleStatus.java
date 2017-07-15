package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.status.StatusType;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public class EffectEndRoundSingleStatus extends EffectEndRoundStatus {

    private StringMap<Rate> multDamageStatus;

    @CheckedData
    private boolean incrementingDamageByRounds;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (String s: multDamageStatus.getKeys()) {
            if (!_data.getStatus().contains(s)) {
                throw new DataException();
            }
            if (_data.getStatus(s).getStatusType() == StatusType.RELATION_UNIQUE) {
                throw new DataException();
            }
        }
        if (!getInflictedRateHpTarget().isZeroOrGt()) {
            throw new DataException();
        }
        if (multDamageStatus.isEmpty()) {
            if (getInflictedRateHpTarget().isZero()) {
                throw new DataException();
            }
            return;
        }
        if (!getInflictedRateHpTarget().isZero()) {
            throw new DataException();
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

    public void setIncrementingDamageByRounds(boolean _incrementingDamageByRounds) {
        incrementingDamageByRounds = _incrementingDamageByRounds;
    }
}
