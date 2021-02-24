package aiki.fight.util;

import code.maths.Rate;
import code.util.CollCapacity;

public final class StatisticTypeRate extends StatisticTypeList<Rate> {
    public StatisticTypeRate() {
    }
    public StatisticTypeRate(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Rate def() {
        return Rate.zero();
    }
}
