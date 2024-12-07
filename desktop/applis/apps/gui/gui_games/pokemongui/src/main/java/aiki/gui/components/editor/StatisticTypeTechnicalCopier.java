package aiki.gui.components.editor;

import aiki.fight.util.*;

public final class StatisticTypeTechnicalCopier implements AbsTechnicalCopier<StatisticType>{
    public StatisticType copy(StatisticType _e){
        StatisticType cp_ = new StatisticType();
        cp_.setStatistic(_e.getStatistic());
        cp_.setType(_e.getType());
        return cp_;
    }
}
