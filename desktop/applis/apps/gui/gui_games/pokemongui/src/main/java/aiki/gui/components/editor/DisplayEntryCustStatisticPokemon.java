package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.core.*;

public final class DisplayEntryCustStatisticPokemon<T> implements DisplayEntryCust<Integer, EditedCrudPair<StatisticPokemon, T>> {
    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String, String> pks;

    public DisplayEntryCustStatisticPokemon(AbsMap<Statistic, String> _s, AbsMap<String, String> _p) {
        this.stats = _s;
        this.pks = _p;
    }


    @Override
    public String display(Integer _k, EditedCrudPair<StatisticPokemon, T> _v) {
        return StringUtil.nullToEmpty(stats.getVal(_v.getKey().getStatistic()))+" "+ StringUtil.nullToEmpty(pks.getVal(_v.getKey().getPokemon()));
    }
}
