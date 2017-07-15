package aiki.comparators;
import java.util.Comparator;

import code.util.EnumMap;
import aiki.DataBase;
import aiki.fight.enums.Statistic;

public class ComparatorStatisticTr implements Comparator<Statistic> {

    private DataBase data;

    private String language;

    public ComparatorStatisticTr(DataBase _data, String _language) {
        data = _data;
        language = _language;
    }

    @Override
    public int compare(Statistic _arg0, Statistic _arg1) {
        EnumMap<Statistic,String> translatedStatistics_ = data.getTranslatedStatistics().getVal(language);
        return translatedStatistics_.getVal(_arg0).compareTo(translatedStatistics_.getVal(_arg1));
    }

}
