package aiki.beans.facade.comparators;
import aiki.comparators.*;
import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.util.*;
import code.util.ints.*;

public final class ComparatorStatusStatistic implements Comparing<StatisticStatus> {

    private final AbsMap<String,String> status;
    private final AbsMap<Statistic,String> statis;

    public ComparatorStatusStatistic(DataBase _data, String _language) {
        this(_data.getTranslatedStatus().getVal(_language),_data.getTranslatedStatistics().getVal(_language));
    }

    public ComparatorStatusStatistic(AbsMap<String,String> _u, AbsMap<Statistic,String> _i) {
        status = _u;
        statis = _i;
    }

    @Override
    public int compare(StatisticStatus _o1, StatisticStatus _o2) {
        return comparePairs(_o1, _o2, status, statis);
    }

    public static int comparePairs(StatisticStatus _o1, StatisticStatus _o2, AbsMap<String,String> _status, AbsMap<Statistic, String> _stats) {
        int res_ = ComparatorTrStrings.compare(_status, _o1.getStatus(), _o2.getStatus());
        if (res_ != 0) {
            return res_;
        }
        return ComparatorTrStrings.compareStatistic(_stats, _o1.getStatistic(), _o2.getStatistic());
    }

}