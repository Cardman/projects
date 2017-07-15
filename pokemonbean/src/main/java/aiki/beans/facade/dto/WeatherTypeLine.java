package aiki.beans.facade.dto;
import code.bean.Accessible;

public class WeatherTypeLine {

    @Accessible
    private String weather;

    @Accessible
    private String type;

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
