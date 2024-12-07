package aiki.gui.components.editor;

import aiki.fight.util.*;

public final class StatisticCategoryTechnicalCopier implements AbsTechnicalCopier<StatisticCategory> {
    @Override
    public StatisticCategory copy(StatisticCategory _e) {
        StatisticCategory cp_ = new StatisticCategory();
        cp_.setStatistic(_e.getStatistic());
        cp_.setCategory(_e.getCategory());
        return cp_;
    }
}
