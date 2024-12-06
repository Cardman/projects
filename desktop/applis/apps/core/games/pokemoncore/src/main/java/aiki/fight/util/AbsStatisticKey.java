package aiki.fight.util;

import aiki.fight.enums.Statistic;

public abstract class AbsStatisticKey {

    private Statistic statistic;

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic _s) {
        this.statistic = _s;
    }
}
