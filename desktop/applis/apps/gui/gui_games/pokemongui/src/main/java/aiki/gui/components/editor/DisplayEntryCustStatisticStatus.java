package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.core.*;

public final class DisplayEntryCustStatisticStatus<T> implements DisplayEntryCust<Integer, EditedCrudPair<StatisticStatus, T>> {
    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String, String> status;

    public DisplayEntryCustStatisticStatus(AbsMap<Statistic, String> _s, AbsMap<String, String> _t) {
        this.stats = _s;
        this.status = _t;
    }


    @Override
    public String display(Integer _k, EditedCrudPair<StatisticStatus, T> _v) {
        return StringUtil.nullToEmpty(stats.getVal(_v.getKey().getStatistic()))+ConverterCommonMapUtil.K_V+ StringUtil.nullToEmpty(status.getVal(_v.getKey().getStatus()));
    }
}
