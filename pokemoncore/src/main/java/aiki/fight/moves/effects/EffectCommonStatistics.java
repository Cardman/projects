package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import code.util.EnumList;
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
        EnumList<Statistic> keys_ = commonValue.getKeys();
        keys_.removeObj(Statistic.PV_RESTANTS);
        if (!Statistic.getStatisticsWithBase().containsAllObj(keys_)) {
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
