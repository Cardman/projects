package aiki.db;

import aiki.fight.util.*;

public final class ChangeStringFieldStatisticType implements ChangeStringField {
    private final StatisticType statisticType;

    public ChangeStringFieldStatisticType(StatisticType _l) {
        this.statisticType = _l;
    }

    @Override
    public String value() {
        return statisticType.getType();
    }

    @Override
    public void value(String _v) {
        statisticType.setType(_v);
    }
}
