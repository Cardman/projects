package aiki.beans.facade.comparators;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import code.util.AbsMap;
import code.util.EnumMap;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorTrStringStatistic implements Comparing<Statistic> {

    private AbsMap<Statistic,String> translator;

    public ComparatorTrStringStatistic(AbsMap<Statistic,String> _translator) {
        translator = _translator;
    }

    @Override
    public int compare(Statistic _e1, Statistic _e2) {
        return compare(translator, _e1, _e2);
    }

    public static int compare(AbsMap<Statistic,String> _translator, Statistic _e1, Statistic _e2) {
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
        return StringUtil.compareStrings(trOne_,trTwo_);
    }
}
