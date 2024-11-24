package aiki.gui.components.editor;

import aiki.beans.facade.comparators.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.ints.*;

public final class ComparingKeyStatisticStatus<T> implements Comparing<EditedCrudPair<StatisticStatus, T>> {

    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String,String> types;

    public ComparingKeyStatisticStatus(AbsMap<Statistic, String> _s, AbsMap<String,String> _t) {
        stats = _s;
        types = _t;
    }

    @Override
    public int compare(EditedCrudPair<StatisticStatus, T> _one, EditedCrudPair<StatisticStatus, T> _two) {
        return ComparatorStatusStatistic.comparePairs(_one.getKey(),_two.getKey(), types, stats);
    }
}
