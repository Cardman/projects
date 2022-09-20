package aiki.beans.facade.comparators;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import code.util.AbsMap;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorTrStringStatistic implements Comparing<Statistic> {

    private final AbsMap<Statistic,String> translator;

    public ComparatorTrStringStatistic(AbsMap<Statistic,String> _translator) {
        translator = _translator;
    }

    @Override
    public int compare(Statistic _e1, Statistic _e2) {
        return compare(translator, _e1, _e2);
    }

    public static int compare(AbsMap<Statistic,String> _translator, Statistic _e1, Statistic _e2) {
        String trOne_ = tr(_translator, _e1);
        String trTwo_ = tr(_translator, _e2);
        return StringUtil.compareStrings(trOne_,trTwo_);
    }

    private static String tr(AbsMap<Statistic, String> _translator, Statistic _e) {
        String tr_;
        if (_translator.contains(_e)) {
            tr_ = _translator.getVal(_e);
        } else {
            tr_ = DataBase.EMPTY_STRING;
        }
        return tr_;
    }
}
