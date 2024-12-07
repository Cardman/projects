package aiki.gui.components.editor;

import aiki.fight.util.*;

public final class StatisticStatusTechnicalCopier implements AbsTechnicalCopier<StatisticStatus> {
    @Override
    public StatisticStatus copy(StatisticStatus _e) {
        return new StatisticStatus(_e.getStatistic(), _e.getStatus());
    }
}
