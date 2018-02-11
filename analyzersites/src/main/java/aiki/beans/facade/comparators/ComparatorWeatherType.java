package aiki.beans.facade.comparators;
import java.util.Comparator;

import code.util.StringMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.util.WeatherType;

public final class ComparatorWeatherType implements Comparator<WeatherType> {

    private DataBase data;

    private String language;

    public ComparatorWeatherType(DataBase _data, String _language) {
        data = _data;
        language = _language;
    }

    @Override
    public int compare(WeatherType _o1, WeatherType _o2) {
        StringMap<String> translatedMovesCmp_;
        translatedMovesCmp_ = data.getTranslatedMoves().getVal(language);
        int res_ = ComparatorTrStrings.compare(translatedMovesCmp_, _o1.getWeather(), _o2.getWeather());
        if (res_ != 0) {
            return res_;
        }
        StringMap<String> translatedTypesCmp_;
        translatedTypesCmp_ = data.getTranslatedTypes().getVal(language);
        return ComparatorTrStrings.compare(translatedTypesCmp_, _o1.getType(), _o2.getType());
    }

}
