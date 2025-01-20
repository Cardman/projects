package aiki.fight.util;

import code.util.CollCapacity;

public final class StatisticTypeByte extends StatisticTypeList<Long> {
    public StatisticTypeByte() {
    }
    public StatisticTypeByte(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Long def() {
        return 0L;
    }
}
