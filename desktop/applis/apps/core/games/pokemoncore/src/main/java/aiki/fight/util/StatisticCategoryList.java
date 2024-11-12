package aiki.fight.util;

import code.util.*;

public abstract class StatisticCategoryList<T> extends AbsBasicMap<StatisticCategory,T> {
    protected StatisticCategoryList() {
    }
    protected StatisticCategoryList(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected boolean matchKeys(StatisticCategory _k, StatisticCategory _e) {
        return _k.eq(_e);
    }

}
