package aiki.beans.facade.comparators;
import aiki.comparators.*;
import aiki.db.*;
import aiki.fight.util.*;
import code.util.*;
import code.util.ints.*;

public final class ComparatorWeatherType implements Comparing<WeatherType> {

    private final AbsMap<String,String> moves;
    private final AbsMap<String,String> types;

    public ComparatorWeatherType(DataBase _data, String _language) {
        this(_data.getTranslatedMoves().getVal(_language),_data.getTranslatedTypes().getVal(_language));
    }

    public ComparatorWeatherType(AbsMap<String,String> _m, AbsMap<String,String> _t) {
        moves = _m;
        types = _t;
    }

    @Override
    public int compare(WeatherType _o1, WeatherType _o2) {
        return comparePairs(_o1, _o2, moves, types);
    }

    public static int comparePairs(WeatherType _o1, WeatherType _o2, AbsMap<String,String> _moves, AbsMap<String,String> _types) {
        int res_ = ComparatorTrStrings.compare(_moves, _o1.getWeather(), _o2.getWeather());
        if (res_ != 0) {
            return res_;
        }
        return ComparatorTrStrings.compare(_types, _o1.getType(), _o2.getType());
    }

}