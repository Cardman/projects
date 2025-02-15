package aiki.beans.facade.comparators;
import aiki.beans.abilities.*;
import aiki.comparators.*;
import aiki.facade.FacadeGame;
import aiki.fight.util.*;
import code.util.*;
import code.util.ints.*;

public final class ComparatorWeatherType implements Comparing<TranslatedKeyPair> {

    private final AbsMap<String,String> moves;
    private final AbsMap<String,String> types;

    public ComparatorWeatherType(FacadeGame _data) {
        this(_data.getTranslatedMoves(),_data.getTranslatedTypes());
    }

    public ComparatorWeatherType(AbsMap<String,String> _m, AbsMap<String,String> _t) {
        moves = _m;
        types = _t;
    }

    @Override
    public int compare(TranslatedKeyPair _o1, TranslatedKeyPair _o2) {
        return comparePairs(new WeatherType(_o1.getFirst().getKey(),_o1.getSecond().getKey()), new WeatherType(_o2.getFirst().getKey(),_o2.getSecond().getKey()), moves, types);
    }

    public static int comparePairs(WeatherType _o1, WeatherType _o2, AbsMap<String,String> _moves, AbsMap<String,String> _types) {
        int res_ = ComparatorTrStrings.compare(_moves, _o1.getWeather(), _o2.getWeather());
        if (res_ != 0) {
            return res_;
        }
        return ComparatorTrStrings.compare(_types, _o1.getType(), _o2.getType());
    }

}