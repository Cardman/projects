package aiki.fight.util;

import code.maths.Rate;
import code.util.CollCapacity;

public final class StatisticCategoryRate extends StatisticCategoryList<Rate> {
    public StatisticCategoryRate() {
    }
    public StatisticCategoryRate(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Rate def() {
        return Rate.zero();
    }

}
