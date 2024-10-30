package aiki.fight.util;

import aiki.util.*;
import code.util.*;

public abstract class StatisticCategoryList<T> extends CommonMap<StatisticCategory,T> {
    protected StatisticCategoryList() {
    }
    protected StatisticCategoryList(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected boolean eq(StatisticCategory _k, StatisticCategory _e) {
        return _k.eq(_e);
    }

}
