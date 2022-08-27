package aiki.beans.facade.comparators;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import code.util.AbsMap;
import code.util.EnumMap;
import code.util.core.StringUtil;
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
        AbsMap<Statistic,String> translatedStatistics_ = data.getTranslatedStatistics().getVal(language);
        return StringUtil.compareStrings(translatedStatistics_.getVal(_arg0),translatedStatistics_.getVal(_arg1));
    }

}