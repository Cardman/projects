package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.ints.*;

public final class ComparingKeyStatisticPokemon<T> implements Comparing<EditedCrudPair<StatisticPokemon, T>> {

    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String,String> types;

    public ComparingKeyStatisticPokemon(AbsMap<Statistic, String> _s, AbsMap<String,String> _t) {
        stats = _s;
        types = _t;
    }

    @Override
    public int compare(EditedCrudPair<StatisticPokemon, T> _one, EditedCrudPair<StatisticPokemon, T> _two) {
        return ConverterCommonMapUtil.compare(ConverterCommonMapUtil.build(_one.getKey(), stats, types),ConverterCommonMapUtil.build(_two.getKey(), stats, types));
    }
}
