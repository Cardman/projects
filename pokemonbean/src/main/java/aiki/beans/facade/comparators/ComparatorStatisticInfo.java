package aiki.beans.facade.comparators;
import code.util.ints.Comparing;

import aiki.beans.facade.fight.StatisticInfo;

public final class ComparatorStatisticInfo implements Comparing<StatisticInfo> {

    @Override
    public int compare(StatisticInfo _o1, StatisticInfo _o2) {
        return _o1.getDisplayStatistic().compareTo(_o2.getDisplayStatistic());
    }

}