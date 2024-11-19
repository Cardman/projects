package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.maths.Rate;
import code.util.*;
import code.util.core.*;

public final class DisplayEntryCustStatisticType implements DisplayEntryCust<Integer, EditedCrudPair<StatisticType, Rate>> {
    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String, String> types;

    public DisplayEntryCustStatisticType(AbsMap<Statistic, String> _s, AbsMap<String, String> _t) {
        this.stats = _s;
        this.types = _t;
    }


    @Override
    public String display(Integer _k, EditedCrudPair<StatisticType, Rate> _v) {
        return StringUtil.nullToEmpty(stats.getVal(_v.getKey().getStatistic()))+" "+ StringUtil.nullToEmpty(types.getVal(_v.getKey().getType()));
    }
}
