package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.core.*;

public final class DisplayEntryCustStatisticCategory<T> implements DisplayEntryCust<Integer, EditedCrudPair<StatisticCategory, T>> {
    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String, String> types;

    public DisplayEntryCustStatisticCategory(AbsMap<Statistic, String> _s, AbsMap<String, String> _t) {
        this.stats = _s;
        this.types = _t;
    }


    @Override
    public String display(Integer _k, EditedCrudPair<StatisticCategory, T> _v) {
        return StringUtil.nullToEmpty(stats.getVal(_v.getKey().getStatistic()))+" "+ StringUtil.nullToEmpty(types.getVal(_v.getKey().getCategory()));
    }
}
