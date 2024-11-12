package aiki.fight.util;

import code.util.*;

public final class StatisticStatusList extends AbsBasicMap<StatisticStatus,Byte> {
    public StatisticStatusList() {
    }
    public StatisticStatusList(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Byte def() {
        return (byte)0;
    }

    @Override
    protected boolean matchKeys(StatisticStatus _k, StatisticStatus _e) {
        return _k.eq(_e);
    }

}
