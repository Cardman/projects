package aiki.fight.util;

import code.util.CollCapacity;

public final class StatisticCategoryByte extends StatisticCategoryList<Byte> {
    public StatisticCategoryByte() {
    }
    public StatisticCategoryByte(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Byte def() {
        return (byte)0;
    }

}
