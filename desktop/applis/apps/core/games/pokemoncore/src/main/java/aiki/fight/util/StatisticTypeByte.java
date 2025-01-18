package aiki.fight.util;

import code.util.CollCapacity;

public final class StatisticTypeByte extends StatisticTypeList<Integer> {
    public StatisticTypeByte() {
    }
    public StatisticTypeByte(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Integer def() {
        return 0;
    }
}
