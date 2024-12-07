package aiki.gui.components.editor;

import aiki.fight.util.*;

public final class WeatherTypeTechnicalCopier implements AbsTechnicalCopier<WeatherType>{
    public WeatherType copy(WeatherType _e){
        WeatherType cp_ = new WeatherType();
        cp_.setWeather(_e.getWeather());
        cp_.setType(_e.getType());
        return cp_;
    }
}
