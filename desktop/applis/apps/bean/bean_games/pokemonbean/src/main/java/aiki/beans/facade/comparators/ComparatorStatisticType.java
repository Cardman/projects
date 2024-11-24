package aiki.beans.facade.comparators;
import aiki.comparators.*;
import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.util.*;
import code.util.ints.*;

public final class ComparatorStatisticType implements Comparing<StatisticType> {

    private final AbsMap<String,String> types;
    private final AbsMap<Statistic,String> statis;

    public ComparatorStatisticType(DataBase _data, String _language) {
        this(_data.getTranslatedTypes().getVal(_language),_data.getTranslatedStatistics().getVal(_language));
    }

    public ComparatorStatisticType(AbsMap<String,String> _u, AbsMap<Statistic,String> _i) {
        types = _u;
        statis = _i;
    }

    @Override
    public int compare(StatisticType _o1, StatisticType _o2) {
        return comparePairs(_o1, _o2, statis, types);
    }

    public static int comparePairs(StatisticType _o1, StatisticType _o2, AbsMap<Statistic, String> _stats, AbsMap<String,String> _types) {
        int res_ = ComparatorTrStrings.compareStatistic(_stats, _o1.getStatistic(), _o2.getStatistic());
        if (res_ != 0) {
            return res_;
        }
        return ComparatorTrStrings.compare(_types, _o1.getType(), _o2.getType());
    }
}