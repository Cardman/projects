package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.StatusType;
import code.datacheck.CheckedData;
import code.util.EnumMap;
import code.util.NumberMap;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public class EffectTeamWhileSendFoe extends Effect {

    @CheckedData
    private String failSending;
    private NumberMap<Short,String> statusByNbUses;
    private StringList deletedByFoeTypes;
    @CheckedData
    private String damageRateAgainstFoe;

    private EnumMap<Statistic,Byte> statistics;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            throw new DataException();
        }
        if (!_data.getTypes().containsAllObj(deletedByFoeTypes)) {
            throw new DataException();
        }
        for (String s: statusByNbUses.values()) {
            if (!_data.getStatus().contains(s)) {
                throw new DataException();
            }
            if (_data.getStatus(s).getStatusType() == StatusType.RELATION_UNIQUE) {
                throw new DataException();
            }
        }
        for (Statistic s: statistics.getKeys()) {
            if (!s.isBoost()) {
                throw new DataException();
            }
        }
    }

    public String getFailSending() {
        return failSending;
    }

    public void setFailSending(String _failSending) {
        failSending = _failSending;
    }

    public NumberMap<Short,String> getStatusByNbUses() {
        return statusByNbUses;
    }
    public void setStatusByNbUses(NumberMap<Short,String> _statusByNbUses) {
        statusByNbUses = _statusByNbUses;
    }
    public StringList getDeletedByFoeTypes() {
        return deletedByFoeTypes;
    }
    public void setDeletedByFoeTypes(StringList _deletedByFoeTypes) {
        deletedByFoeTypes = _deletedByFoeTypes;
    }
    public String getDamageRateAgainstFoe() {
        return damageRateAgainstFoe;
    }
    public void setDamageRateAgainstFoe(String _damageRateAgainstFoe) {
        damageRateAgainstFoe = _damageRateAgainstFoe;
    }

    public EnumMap<Statistic,Byte> getStatistics() {
        return statistics;
    }

    public void setStatistics(EnumMap<Statistic,Byte> _statistics) {
        statistics = _statistics;
    }
}
