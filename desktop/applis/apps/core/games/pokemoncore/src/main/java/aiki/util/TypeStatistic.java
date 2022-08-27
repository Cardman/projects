package aiki.util;

import aiki.fight.enums.Statistic;
import code.util.core.StringUtil;

public final class TypeStatistic {

    private final String type;

    private final Statistic stat;

    public TypeStatistic(String _type, Statistic _stat) {
        type = _type;
        stat = _stat;
    }

    public String getType() {
        return type;
    }

    public Statistic getStat() {
        return stat;
    }

    public boolean eq(TypeStatistic _g) {
        if (getStat() != _g.getStat()) {
            return false;
        }
        return StringUtil.quickEq(getType(), _g.getType());
    }

}
