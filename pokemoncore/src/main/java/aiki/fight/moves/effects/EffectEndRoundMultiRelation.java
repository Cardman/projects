package aiki.fight.moves.effects;
import code.maths.Rate;
import code.util.StringMap;
import code.util.annot.RwXml;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.status.StatusType;

@RwXml
public class EffectEndRoundMultiRelation extends EffectEndRound {

    private StringMap<Rate> damageByStatus;
    private StringMap<Rate> multDamageStatus;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (String s: damageByStatus.getKeys()) {
            if (!_data.getStatus().contains(s)) {
                throw new DataException();
            }
            if (_data.getStatus(s).getStatusType() == StatusType.RELATION_UNIQUE) {
                throw new DataException();
            }
        }
        for (String s: multDamageStatus.getKeys()) {
            if (!_data.getStatus().contains(s)) {
                throw new DataException();
            }
            if (_data.getStatus(s).getStatusType() == StatusType.RELATION_UNIQUE) {
                throw new DataException();
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
    public StringMap<Rate> getMultDamageStatus() {
        return multDamageStatus;
    }
    public void setMultDamageStatus(StringMap<Rate> _multDamageStatus) {
        multDamageStatus = _multDamageStatus;
    }
}
