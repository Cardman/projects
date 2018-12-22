package aiki.fight.effects;

import aiki.db.DataBase;
import code.maths.Rate;


public class EffectWhileSending {

    private boolean disableWeather;
    private String enabledWeather;
    private boolean copyingAbility;
    private boolean plate;
    private Rate multWeight;

    public void validate(DataBase _data) {
        if (!multWeight.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (disableWeather) {
            if (!enabledWeather.isEmpty()) {
                _data.setError(true);
                return;

            }
        } else {
            if (!enabledWeather.isEmpty()) {
                if (!_data.getMovesEffectGlobalWeather().containsObj(
                        enabledWeather)) {
                    _data.setError(true);
                    return;

                }
            }
        }
    }

    public boolean getDisableWeather() {
        return disableWeather;
    }

    public void setDisableWeather(boolean _disableWeather) {
        disableWeather = _disableWeather;
    }

    public String getEnabledWeather() {
        return enabledWeather;
    }

    public void setEnabledWeather(String _enabledWeather) {
        enabledWeather = _enabledWeather;
    }

    public boolean getCopyingAbility() {
        return copyingAbility;
    }

    public void setCopyingAbility(boolean _copyingAbility) {
        copyingAbility = _copyingAbility;
    }

    public boolean getPlate() {
        return plate;
    }

    public void setPlate(boolean _plate) {
        plate = _plate;
    }

    public Rate getMultWeight() {
        return multWeight;
    }

    public void setMultWeight(Rate _multWeight) {
        multWeight = _multWeight;
    }
}
