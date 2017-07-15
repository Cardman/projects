package aiki.beans.facade.comparators;
import java.util.Comparator;

import code.util.Numbers;
import aiki.fight.enums.Statistic;

public class ComparatorStatistic implements Comparator<Statistic> {

    @Override
    public int compare(Statistic _arg0, Statistic _arg1) {
        return Numbers.compare(_arg0.ordinal(), _arg1.ordinal());
    }
}
