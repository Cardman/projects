package aiki.fight.util;

import code.util.*;

public final class StatisticStatusList extends AbsBasicMap<StatisticStatus,Long> {
    public StatisticStatusList() {
    }
    public StatisticStatusList(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Long def() {
        return 0L;
    }

    @Override
    protected boolean matchKeys(StatisticStatus _k, StatisticStatus _e) {
        return _k.eq(_e);
    }

}
