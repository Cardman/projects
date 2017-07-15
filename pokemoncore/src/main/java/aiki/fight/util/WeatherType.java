package aiki.fight.util;
import code.datacheck.CheckedData;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;

@CheckedData
public final class WeatherType implements Equallable<WeatherType> {

    private static final char SEPARATOR = ';';

    private String weather;

    private String type;

    public WeatherType(){
    }

    public WeatherType(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        weather = elements_.first();
        type = elements_.last();
    }

    public WeatherType(String _weather, String _type) {
        weather = _weather;
        type = _type;
    }

    @FromAndToString
    public static WeatherType newWeatherType(String _string) {
        return new WeatherType(_string);
    }

    @Override
    public boolean eq(WeatherType _obj) {
        if (!StringList.quickEq(weather, _obj.weather)) {
            return false;
        }
        if (!StringList.quickEq(type, _obj.type)) {
            return false;
        }
        return true;
    }

    @FromAndToString
    @Override
    public String toString() {
        return weather+SEPARATOR+type;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String _weather) {
        weather = _weather;
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }
}
