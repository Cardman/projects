package aiki.fight.util;

import code.util.CollCapacity;

public final class StatisticCategoryByte extends StatisticCategoryList<Integer> {
    public StatisticCategoryByte() {
    }
    public StatisticCategoryByte(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Integer def() {
        return 0;
    }

}
