package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.DataBase;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.util.StatisticStatus;
import code.util.EnumMap;
import code.util.StringMap;

public final class ComparatorStatusStatistic implements Comparator<StatisticStatus> {

    private DataBase data;

    private String language;

    public ComparatorStatusStatistic(DataBase _data, String _language) {
        data = _data;
        language = _language;
    }

    @Override
    public int compare(StatisticStatus _o1, StatisticStatus _o2) {
        StringMap<String> translatedStatusCmp_;
        translatedStatusCmp_ = data.getTranslatedStatus().getVal(language);
        int res_ = ComparatorTrStrings.compare(translatedStatusCmp_, _o1.getStatus(), _o2.getStatus());
        if (res_ != 0) {
            return res_;
        }
        EnumMap<Statistic,String> translatedStatisticsCmp_;
        translatedStatisticsCmp_ = data.getTranslatedStatistics().getVal(language);
        return ComparatorTrStringStatistic.compare(translatedStatisticsCmp_, _o1.getStatistic(), _o2.getStatistic());
    }

}