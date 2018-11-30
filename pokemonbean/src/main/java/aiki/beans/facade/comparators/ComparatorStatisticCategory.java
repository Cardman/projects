package aiki.beans.facade.comparators;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.util.StatisticCategory;
import code.util.EnumMap;
import code.util.StringMap;
import code.util.ints.Comparing;

public final class ComparatorStatisticCategory implements Comparing<StatisticCategory> {

    private DataBase data;

    private String language;

    public ComparatorStatisticCategory(DataBase _data, String _language) {
        data = _data;
        language = _language;
    }

    @Override
    public int compare(StatisticCategory _o1, StatisticCategory _o2) {
        EnumMap<Statistic,String> translatedStatisticsCmp_;
        translatedStatisticsCmp_ = data.getTranslatedStatistics().getVal(language);
        int res_ = ComparatorTrStringStatistic.compare(translatedStatisticsCmp_, _o1.getStatistic(), _o2.getStatistic());
        if (res_ != 0) {
            return res_;
        }
        StringMap<String> translatedCategoriesCmp_;
        translatedCategoriesCmp_ = data.getTranslatedCategories().getVal(language);
        return ComparatorTrStrings.compare(translatedCategoriesCmp_, _o1.getCategory(), _o2.getCategory());
    }

}