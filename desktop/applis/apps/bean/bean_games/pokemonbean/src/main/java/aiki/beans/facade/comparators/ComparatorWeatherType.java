package aiki.beans.facade.comparators;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.util.WeatherType;
import code.util.StringMap;
import code.util.ints.Comparing;

public final class ComparatorWeatherType implements Comparing<WeatherType> {

    private final DataBase data;

    private final String language;

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