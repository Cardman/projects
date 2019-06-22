package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import code.util.CustList;
import code.util.EnumMap;


public final class EffectCommonStatistics extends Effect {

    private EnumMap<Statistic, String> commonValue;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (commonValue.contains(Statistic.HP)) {
            _data.setError(true);
            return;

        }
        CustList<Statistic> keys_ = commonValue.getKeys();
        CustList<Statistic> keysFilt_ = new CustList<Statistic>();
        for (Statistic s: keys_) {
            if (s != Statistic.PV_RESTANTS) {
                keysFilt_.add(s);
            }
        }
        if (!Statistic.getStatisticsWithBase().containsAllObj(keysFilt_)) {
            _data.setError(true);
            return;

        }
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            _data.setError(true);

        }
    }

    public EnumMap<Statistic, String> getCommonValue() {
        return commonValue;
    }

    public void setCommonValue(EnumMap<Statistic, String> _commonValue) {
        commonValue = _commonValue;
    }

}
