package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.StatusType;
import aiki.util.DataInfoChecker;
import code.util.IdMap;
import code.util.*;
import code.util.StringList;


public final class EffectTeamWhileSendFoe extends Effect {

    private String failSending;
    private IntMap< String> statusByNbUses;
    private StringList deletedByFoeTypes;
    private String damageRateAgainstFoe;

    private IdMap<Statistic, Integer> statistics;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTarget(TargetChoice.LANCEUR,getTargetChoice(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),deletedByFoeTypes,_data);
        DataInfoChecker.checkStringListContains(DataInfoChecker.filterStatusExclude(_data,StatusType.RELATION_UNIQUE).getKeys(),statusByNbUses.values(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),statistics.getKeys(),_data);
    }

    public String getFailSending() {
        return failSending;
    }

    public void setFailSending(String _failSending) {
        failSending = _failSending;
    }

    public IntMap< String> getStatusByNbUses() {
        return statusByNbUses;
    }

    public void setStatusByNbUses(IntMap< String> _statusByNbUses) {
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

    public IdMap<Statistic, Integer> getStatistics() {
        return statistics;
    }

    public void setStatistics(IdMap<Statistic, Integer> _statistics) {
        statistics = _statistics;
    }
}
