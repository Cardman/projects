package aiki.db;

import aiki.fight.util.*;

public final class ChangeStringFieldWeatherTypeMove implements ChangeStringField {
    private final WeatherType weatherType;

    public ChangeStringFieldWeatherTypeMove(WeatherType _l) {
        this.weatherType = _l;
    }

    @Override
    public String value() {
        return weatherType.getWeather();
    }

    @Override
    public void value(String _v) {
        weatherType.setWeather(_v);
    }
}
