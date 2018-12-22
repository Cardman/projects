package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.StatusType;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.NumberMap;
import code.util.StringList;


public final class EffectTeamWhileSendFoe extends Effect {

    private String failSending;
    private NumberMap<Short, String> statusByNbUses;
    private StringList deletedByFoeTypes;
    private String damageRateAgainstFoe;

    private EnumMap<Statistic, Byte> statistics;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            _data.setError(true);
            return;

        }
        if (!_data.getTypes().containsAllObj(deletedByFoeTypes)) {
            _data.setError(true);
            return;

        }
        for (EntryCust<Short, String> e : statusByNbUses.entryList()) {
            if (!_data.getStatus().contains(e.getValue())) {
                _data.setError(true);
                return;

            }
            if (_data.getStatus(e.getValue()).getStatusType() == StatusType.RELATION_UNIQUE) {
                _data.setError(true);
                return;

            }
            e.getKey().shortValue();
        }
        for (EntryCust<Statistic, Byte> s : statistics.entryList()) {
            if (!s.getKey().isBoost()) {
                _data.setError(true);
                return;

            }
            s.getValue().byteValue();
        }
    }

    public String getFailSending() {
        return failSending;
    }

    public void setFailSending(String _failSending) {
        failSending = _failSending;
    }

    public NumberMap<Short, String> getStatusByNbUses() {
        return statusByNbUses;
    }

    public void setStatusByNbUses(NumberMap<Short, String> _statusByNbUses) {
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

    public EnumMap<Statistic, Byte> getStatistics() {
        return statistics;
    }

    public void setStatistics(EnumMap<Statistic, Byte> _statistics) {
        statistics = _statistics;
    }
}
