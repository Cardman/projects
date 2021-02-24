package aiki.fight.util;

import code.maths.Rate;

public final class WeatherTypeRate {

    private final WeatherType stat;
    private final Rate value;

    public WeatherTypeRate(WeatherType _stat, Rate _value) {
        this.stat = _stat;
        this.value = _value;
    }

    public WeatherType getStat() {
        return stat;
    }

    public Rate getValue() {
        return value;
    }
}
