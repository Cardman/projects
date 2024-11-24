package aiki.gui.components.editor;

import aiki.beans.facade.comparators.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.ints.*;

public final class ComparingKeyStatisticCategory<T> implements Comparing<EditedCrudPair<StatisticCategory, T>> {

    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String,String> types;

    public ComparingKeyStatisticCategory(AbsMap<Statistic, String> _s, AbsMap<String,String> _t) {
        stats = _s;
        types = _t;
    }

    @Override
    public int compare(EditedCrudPair<StatisticCategory, T> _one, EditedCrudPair<StatisticCategory, T> _two) {
        return ComparatorStatisticCategory.comparePairs(_one.getKey(),_two.getKey(), stats, types);
    }
}
