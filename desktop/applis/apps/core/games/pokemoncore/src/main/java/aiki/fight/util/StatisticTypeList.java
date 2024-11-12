package aiki.fight.util;

import aiki.fight.enums.*;
import code.util.*;

public abstract class StatisticTypeList<T> extends AbsBasicMap<StatisticType,T> {
    protected StatisticTypeList() {
    }
    protected StatisticTypeList(CollCapacity _cap) {
        super(_cap);
    }


    public CustList<Statistic> getStatistics() {
        CustList<Statistic> l_ = new CustList<Statistic>();
        for (StatisticType e: getKeys()) {
            l_.add(e.getStatistic());
        }
        return l_;
    }

    public CustList<String> getTypes() {
        CustList<String> l_ = new CustList<String>();
        for (StatisticType e: getKeys()) {
            l_.add(e.getType());
        }
        return l_;
    }

    @Override
    protected boolean matchKeys(StatisticType _k, StatisticType _e) {
        return _k.eq(_e);
    }

}
