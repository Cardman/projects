package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.util.CustList;
import code.util.IdMap;


public final class EffectCommonStatistics extends Effect {

    private IdMap<Statistic, String> commonValue;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (commonValue.contains(Statistic.HP)) {
            _data.setError(true);
        }
        CustList<Statistic> keys_ = commonValue.getKeys();
        CustList<Statistic> keysFilt_ = new CustList<Statistic>();
        for (Statistic s: keys_) {
            if (s != Statistic.PV_RESTANTS) {
                keysFilt_.add(s);
            }
        }
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBase(),keysFilt_,_data);
        DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
    }

    public IdMap<Statistic, String> getCommonValue() {
        return commonValue;
    }

    public void setCommonValue(IdMap<Statistic, String> _commonValue) {
        commonValue = _commonValue;
    }

}
