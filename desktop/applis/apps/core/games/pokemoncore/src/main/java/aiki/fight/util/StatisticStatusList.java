package aiki.fight.util;

import aiki.util.*;
import code.util.*;

public final class StatisticStatusList extends CommonMap<StatisticStatus,Byte> {
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
    protected boolean eq(StatisticStatus _k, StatisticStatus _e) {
        return _k.eq(_e);
    }

}
