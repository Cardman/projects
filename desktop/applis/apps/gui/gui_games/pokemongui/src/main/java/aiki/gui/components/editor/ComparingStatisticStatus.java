package aiki.gui.components.editor;

import aiki.beans.facade.comparators.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.util.*;
import code.util.ints.*;

public final class ComparingStatisticStatus implements Comparing<StatisticStatus> {

    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String,String> types;

    public ComparingStatisticStatus(AbsMap<Statistic, String> _s, AbsMap<String,String> _t) {
        stats = _s;
        types = _t;
    }

    @Override
    public int compare(StatisticStatus _one, StatisticStatus _two) {
        return ComparatorStatusStatistic.comparePairs(_one,_two, types, stats);
    }
}
