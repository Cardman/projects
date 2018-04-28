package aiki.beans.facade.comparators;
import code.util.ints.Comparing;

import aiki.fight.enums.Statistic;
import code.util.Numbers;

public final class ComparatorStatistic implements Comparing<Statistic> {

    @Override
    public int compare(Statistic _arg0, Statistic _arg1) {
        return Numbers.compare(_arg0.ordinal(), _arg1.ordinal());
    }
}