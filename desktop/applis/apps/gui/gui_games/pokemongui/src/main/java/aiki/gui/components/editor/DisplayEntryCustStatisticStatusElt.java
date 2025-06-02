package aiki.gui.components.editor;

import aiki.fight.enums.Statistic;
import aiki.fight.util.StatisticStatus;
import code.gui.DisplayEntryCust;
import code.util.AbsMap;
import code.util.core.StringUtil;

public final class DisplayEntryCustStatisticStatusElt implements DisplayEntryCust<Integer, StatisticStatus> {
    private final AbsMap<String, String> status;
    private final AbsMap<Statistic, String> stats;

    public DisplayEntryCustStatisticStatusElt(AbsMap<String, String> _t, AbsMap<Statistic, String> _s) {
        this.status = _t;
        this.stats = _s;
    }


    @Override
    public String display(Integer _k, StatisticStatus _v) {
        return StringUtil.nullToEmpty(stats.getVal(_v.getStatistic()))+ConverterCommonMapUtil.K_V+ StringUtil.nullToEmpty(status.getVal(_v.getStatus()));
    }
}
