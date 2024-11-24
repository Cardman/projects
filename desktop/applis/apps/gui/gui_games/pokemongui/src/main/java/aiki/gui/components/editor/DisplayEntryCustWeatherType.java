package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.core.*;

public final class DisplayEntryCustWeatherType<T> implements DisplayEntryCust<Integer, EditedCrudPair<WeatherType, T>> {
    private final AbsMap<String, String> moves;
    private final AbsMap<String, String> types;

    public DisplayEntryCustWeatherType(AbsMap<String, String> _m, AbsMap<String, String> _t) {
        this.moves = _m;
        this.types = _t;
    }


    @Override
    public String display(Integer _k, EditedCrudPair<WeatherType, T> _v) {
        return StringUtil.nullToEmpty(moves.getVal(_v.getKey().getWeather()))+" "+ StringUtil.nullToEmpty(types.getVal(_v.getKey().getType()));
    }
}
