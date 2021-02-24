package aiki.fight.util;

import code.util.CollCapacity;

public final class StatisticTypeByte extends StatisticTypeList<Byte> {
    public StatisticTypeByte() {
    }
    public StatisticTypeByte(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Byte def() {
        return (byte)0;
    }
}
