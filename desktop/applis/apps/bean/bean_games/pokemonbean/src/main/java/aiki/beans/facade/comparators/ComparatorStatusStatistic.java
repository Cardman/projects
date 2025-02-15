package aiki.beans.facade.comparators;
import aiki.beans.abilities.TranslatedKeyPair;
import aiki.comparators.*;
import aiki.facade.FacadeGame;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.util.*;
import code.util.ints.*;

public final class ComparatorStatusStatistic implements Comparing<TranslatedKeyPair> {

    private final AbsMap<String,String> status;
    private final AbsMap<Statistic,String> statis;

    public ComparatorStatusStatistic(FacadeGame _data) {
        this(_data.getTranslatedStatus(),_data.getTranslatedStatistics());
    }

    public ComparatorStatusStatistic(AbsMap<String,String> _u, AbsMap<Statistic,String> _i) {
        status = _u;
        statis = _i;
    }

    @Override
    public int compare(TranslatedKeyPair _o1, TranslatedKeyPair _o2) {
        return comparePairs(convert(_o1), convert(_o2), status, statis);
    }

    private StatisticStatus convert(TranslatedKeyPair _o) {
        return new StatisticStatus(Statistic.getStatisticByName(_o.getFirst().getKey()), _o.getSecond().getKey());
    }

    public static int comparePairs(StatisticStatus _o1, StatisticStatus _o2, AbsMap<String,String> _status, AbsMap<Statistic, String> _stats) {
        int res_ = ComparatorTrStrings.compare(_status, _o1.getStatus(), _o2.getStatus());
        if (res_ != 0) {
            return res_;
        }
        return ComparatorTrStrings.compareStatistic(_stats, _o1.getStatistic(), _o2.getStatistic());
    }

}