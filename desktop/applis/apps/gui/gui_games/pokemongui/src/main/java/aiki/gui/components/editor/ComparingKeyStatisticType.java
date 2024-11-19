package aiki.gui.components.editor;

import aiki.beans.facade.comparators.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.maths.*;
import code.util.*;
import code.util.ints.*;

public final class ComparingKeyStatisticType implements Comparing<EditedCrudPair<StatisticType, Rate>> {

    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String,String> cats;

    public ComparingKeyStatisticType(AbsMap<Statistic, String> _s, AbsMap<String,String> _c) {
        stats = _s;
        cats = _c;
    }

    @Override
    public int compare(EditedCrudPair<StatisticType, Rate> _one, EditedCrudPair<StatisticType, Rate> _two) {
        return ComparatorStatisticType.comparePairs(_one.getKey(),_two.getKey(), stats, cats);
    }
}
