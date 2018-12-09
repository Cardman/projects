package aiki.comparators;
import aiki.DataBase;
import aiki.fight.enums.Statistic;
import code.util.EnumMap;
import code.util.ints.Comparing;

public final class ComparatorStatisticTr implements Comparing<Statistic> {

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
