package aiki.db;

import aiki.fight.util.WeatherType;

public final class ChangeStringFieldWeatherType implements ChangeStringField {
    private final WeatherType weatherType;

    public ChangeStringFieldWeatherType(WeatherType _l) {
        this.weatherType = _l;
    }

    @Override
    public String value() {
        return weatherType.getType();
    }

    @Override
    public void value(String _v) {
        weatherType.setType(_v);
    }
}
