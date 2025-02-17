package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.ints.*;

public final class ComparingKeyStatisticType<T> implements Comparing<EditedCrudPair<StatisticType, T>> {

    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String,String> types;

    public ComparingKeyStatisticType(AbsMap<Statistic, String> _s, AbsMap<String,String> _t) {
        stats = _s;
        types = _t;
    }

    @Override
    public int compare(EditedCrudPair<StatisticType, T> _one, EditedCrudPair<StatisticType, T> _two) {
        return ConverterCommonMapUtil.compare(ConverterCommonMapUtil.build(_one.getKey(), stats, types),ConverterCommonMapUtil.build(_two.getKey(), stats, types));
//        return ComparatorStatisticType.comparePairs(_one.getKey(),_two.getKey(), stats, types);
    }
}
