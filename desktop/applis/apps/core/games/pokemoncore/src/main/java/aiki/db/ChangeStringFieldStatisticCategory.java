package aiki.db;

import aiki.fight.util.*;

public final class ChangeStringFieldStatisticCategory implements ChangeStringField {
    private final StatisticCategory statisticCategory;

    public ChangeStringFieldStatisticCategory(StatisticCategory _l) {
        this.statisticCategory = _l;
    }

    @Override
    public String value() {
        return statisticCategory.getCategory();
    }

    @Override
    public void value(String _v) {
        statisticCategory.setCategory(_v);
    }
}
