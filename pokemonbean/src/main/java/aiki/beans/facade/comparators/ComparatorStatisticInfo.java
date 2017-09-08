package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.beans.facade.fight.StatisticInfo;

public final class ComparatorStatisticInfo implements Comparator<StatisticInfo> {

    @Override
    public int compare(StatisticInfo _o1, StatisticInfo _o2) {
        return _o1.getDisplayStatistic().compareTo(_o2.getDisplayStatistic());
    }

}
