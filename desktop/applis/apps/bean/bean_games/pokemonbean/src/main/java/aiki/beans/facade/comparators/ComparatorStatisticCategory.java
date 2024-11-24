package aiki.beans.facade.comparators;
import aiki.comparators.*;
import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.util.*;
import code.util.ints.*;

public final class ComparatorStatisticCategory implements Comparing<StatisticCategory> {

    private final IdMap<Statistic, String> stats;
    private final AbsMap<String,String> cats;

    public ComparatorStatisticCategory(DataBase _data, String _language) {
        this(_data.getTranslatedStatistics().getVal(_language),_data.getTranslatedCategories().getVal(_language));
    }

    public ComparatorStatisticCategory(IdMap<Statistic, String> _s, AbsMap<String,String> _c) {
        stats = _s;
        cats = _c;
    }

    @Override
    public int compare(StatisticCategory _o1, StatisticCategory _o2) {
        return comparePairs(_o1, _o2, stats, cats);
    }

    public static int comparePairs(StatisticCategory _o1, StatisticCategory _o2, AbsMap<Statistic, String> _stats, AbsMap<String, String> _cats) {
        int res_ = ComparatorTrStrings.compareStatistic(_stats, _o1.getStatistic(), _o2.getStatistic());
        if (res_ != 0) {
            return res_;
        }
        return ComparatorTrStrings.compare(_cats, _o1.getCategory(), _o2.getCategory());
    }

}