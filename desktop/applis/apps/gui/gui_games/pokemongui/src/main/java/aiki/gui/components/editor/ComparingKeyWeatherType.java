package aiki.gui.components.editor;

import aiki.beans.facade.comparators.*;
import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.ints.*;

public final class ComparingKeyWeatherType<T> implements Comparing<EditedCrudPair<WeatherType, T>> {

    private final AbsMap<String, String> moves;
    private final AbsMap<String,String> types;

    public ComparingKeyWeatherType(AbsMap<String, String> _m, AbsMap<String,String> _t) {
        moves = _m;
        types = _t;
    }

    @Override
    public int compare(EditedCrudPair<WeatherType, T> _one, EditedCrudPair<WeatherType, T> _two) {
        return ComparatorWeatherType.comparePairs(_one.getKey(),_two.getKey(), moves, types);
    }
}
