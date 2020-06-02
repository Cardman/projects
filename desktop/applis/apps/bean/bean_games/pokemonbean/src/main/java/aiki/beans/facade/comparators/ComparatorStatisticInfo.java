package aiki.beans.facade.comparators;
import aiki.beans.facade.fight.StatisticInfo;
import code.util.ints.Comparing;

public final class ComparatorStatisticInfo implements Comparing<StatisticInfo> {

    @Override
    public int compare(StatisticInfo _o1, StatisticInfo _o2) {
        return _o1.getDisplayStatistic().compareTo(_o2.getDisplayStatistic());
    }

}