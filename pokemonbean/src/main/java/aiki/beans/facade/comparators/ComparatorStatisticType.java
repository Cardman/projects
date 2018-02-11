package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.DataBase;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.util.StatisticType;
import code.util.EnumMap;
import code.util.StringMap;

public final class ComparatorStatisticType implements Comparator<StatisticType> {

    private DataBase data;

    private String language;

    public ComparatorStatisticType(DataBase _data, String _language) {
        data = _data;
        language = _language;
    }

    @Override
    public int compare(StatisticType _o1, StatisticType _o2) {
        EnumMap<Statistic,String> translatedStatisticsCmp_;
        translatedStatisticsCmp_ = data.getTranslatedStatistics().getVal(language);
        int res_ = ComparatorTrStringStatistic.compare(translatedStatisticsCmp_, _o1.getStatistic(), _o2.getStatistic());
        if (res_ != 0) {
            return res_;
        }
        StringMap<String> translatedCategoriesCmp_;
        translatedCategoriesCmp_ = data.getTranslatedTypes().getVal(language);
        return ComparatorTrStrings.compare(translatedCategoriesCmp_, _o1.getType(), _o2.getType());
    }
}