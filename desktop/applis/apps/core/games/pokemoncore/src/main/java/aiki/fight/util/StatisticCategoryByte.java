package aiki.fight.util;

import code.util.CollCapacity;

public final class StatisticCategoryByte extends StatisticCategoryList<Long> {
    public StatisticCategoryByte() {
    }
    public StatisticCategoryByte(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Long def() {
        return 0L;
    }

}
