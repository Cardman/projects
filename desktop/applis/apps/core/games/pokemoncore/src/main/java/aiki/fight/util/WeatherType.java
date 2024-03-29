package aiki.fight.util;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class WeatherType implements Displayable {

    private static final char SEPARATOR = ';';

    private String weather;

    private String type;

    public WeatherType(){
    }

    public WeatherType(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        weather = elements_.first();
        type = elements_.last();
    }

    public WeatherType(String _weather, String _type) {
        weather = _weather;
        type = _type;
    }

    
    public static WeatherType newWeatherType(String _string) {
        return new WeatherType(_string);
    }

    public boolean eq(WeatherType _obj) {
        if (!StringUtil.quickEq(weather, _obj.weather)) {
            return false;
        }
        return StringUtil.quickEq(type, _obj.type);
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

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(weather);
        str_.append(SEPARATOR);
        str_.append(type);
        return str_.toString();
    }
}
