package aiki.db;

import aiki.fight.util.*;

public final class ChangeStringFieldStatisticStatus implements ChangeStringField {
    private final StatisticStatus statisticCategory;

    public ChangeStringFieldStatisticStatus(StatisticStatus _l) {
        this.statisticCategory = _l;
    }

    @Override
    public String value() {
        return statisticCategory.getStatus();
    }

    @Override
    public void value(String _v) {
        statisticCategory.setStatus(_v);
    }
}
