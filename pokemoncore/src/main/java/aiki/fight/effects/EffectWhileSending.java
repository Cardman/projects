package aiki.fight.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public class EffectWhileSending {

    private boolean disableWeather;
    private String enabledWeather;
    private boolean copyingAbility;
    private boolean plate;
    private Rate multWeight;

    public void validate(DataBase _data) {
        if (!multWeight.isZeroOrGt()) {
            throw new DataException();
        }
        if (disableWeather) {
            if (!enabledWeather.isEmpty()) {
                throw new DataException();
            }
        } else {
            if (!enabledWeather.isEmpty()) {
                if (!_data.getMovesEffectGlobalWeather().containsObj(enabledWeather)) {
                    throw new DataException();
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
