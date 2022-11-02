package aiki.beans.facade.comparators;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.util.StatisticType;
import code.util.AbsMap;
import code.util.StringMap;
import code.util.ints.Comparing;

public final class ComparatorStatisticType implements Comparing<StatisticType> {

    private final DataBase data;

    private final String language;

    public ComparatorStatisticType(DataBase _data, String _language) {
        data = _data;
        language = _language;
    }

    @Override
    public int compare(StatisticType _o1, StatisticType _o2) {
        AbsMap<Statistic,String> translatedStatisticsCmp_;
        translatedStatisticsCmp_ = data.getTranslatedStatistics().getVal(language);
        int res_ = ComparatorTrStrings.compareStatistic(translatedStatisticsCmp_, _o1.getStatistic(), _o2.getStatistic());
        if (res_ != 0) {
            return res_;
        }
        StringMap<String> translatedCategoriesCmp_;
        translatedCategoriesCmp_ = data.getTranslatedTypes().getVal(language);
        return ComparatorTrStrings.compare(translatedCategoriesCmp_, _o1.getType(), _o2.getType());
    }
}