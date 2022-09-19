package aiki.fight.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public class EffectWhileSending {

    private boolean disableWeather;
    private String enabledWeather;
    private boolean copyingAbility;
    private Rate multWeight;

    public void validate(DataBase _data) {
        DataInfoChecker.checkPositiveOrZero(multWeight,_data);
        if (disableWeather) {
            DataInfoChecker.checkEmptyString(enabledWeather,_data);
        } else {
            DataInfoChecker.checkStringListContainsOrEmpty(_data.getMovesEffectGlobalWeather(), enabledWeather,_data);
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

    public Rate getMultWeight() {
        return multWeight;
    }

    public void setMultWeight(Rate _multWeight) {
        multWeight = _multWeight;
    }
}
