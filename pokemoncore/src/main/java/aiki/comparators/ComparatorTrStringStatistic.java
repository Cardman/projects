package aiki.comparators;
import code.util.ints.Comparing;

import aiki.DataBase;
import aiki.fight.enums.Statistic;
import code.util.EnumMap;

public final class ComparatorTrStringStatistic implements Comparing<Statistic> {

    private EnumMap<Statistic,String> translator;

    public ComparatorTrStringStatistic(EnumMap<Statistic,String> _translator) {
        translator = _translator;
    }

    @Override
    public int compare(Statistic _e1, Statistic _e2) {
        return compare(translator, _e1, _e2);
    }

    public static int compare(EnumMap<Statistic,String> _translator, Statistic _e1, Statistic _e2) {
        String trOne_;
        if (_translator.contains(_e1)) {
            trOne_ = _translator.getVal(_e1);
        } else {
            trOne_ = DataBase.EMPTY_STRING;
        }
        String trTwo_;
        if (_translator.contains(_e2)) {
            trTwo_ = _translator.getVal(_e2);
        } else {
            trTwo_ = DataBase.EMPTY_STRING;
        }
        return trOne_.compareTo(trTwo_);
    }
}
