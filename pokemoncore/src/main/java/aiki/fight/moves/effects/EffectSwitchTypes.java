package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.ConstValuesType;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.map.levels.enums.EnvironmentType;
import code.serialize.CheckedData;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public class EffectSwitchTypes extends Effect {

    private EnumMap<EnvironmentType,String> chgtTypeByEnv;
    @CheckedData
    private ConstValuesType constValuesType;
    @CheckedData
    private ExchangeType exchangeTypes;
    private StringList constTypes;
    private StringList addedTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getTypes().containsAllObj(constTypes)) {
            throw new DataException();
        }
        if (!_data.getTypes().containsAllObj(addedTypes)) {
            throw new DataException();
        }
        if (constValuesType == null) {
            throw new DataException();
        }
        if (!chgtTypeByEnv.isEmpty()) {
            if (constValuesType != ConstValuesType.NOTHING) {
                throw new DataException();
            }
            for (EntryCust<EnvironmentType,String> e: chgtTypeByEnv.entryList()) {
                if (!EnvironmentType.getEnvironments().containsObj(e.getKey())) {
                    throw new DataException();
                }
                if (!_data.getTypes().containsStr(e.getValue())) {
                    throw new DataException();
                }
            }
            return;
        }
        if (exchangeTypes != ExchangeType.NOTHING) {
            if (constValuesType != ConstValuesType.NOTHING) {
                throw new DataException();
            }
            boolean checkTargetChoice_ = false;
            if (exchangeTypes == ExchangeType.GIVE_TO_TARGET) {
                checkTargetChoice_ = true;
            } else if (exchangeTypes == ExchangeType.GIVE_TO_THROWER) {
                checkTargetChoice_ = true;
            } else if (exchangeTypes == ExchangeType.EXCHANGE) {
                checkTargetChoice_ = true;
            }
            if (checkTargetChoice_) {
                if (getTargetChoice() == TargetChoice.LANCEUR) {
                    throw new DataException();
                }
                return;
            }
            if (exchangeTypes == ExchangeType.GIVE_CONST) {
                if (constTypes.isEmpty()) {
                    throw new DataException();
                }
                return;
            }
            throw new DataException();
        }
    }

    public EnumMap<EnvironmentType,String> getChgtTypeByEnv() {
        return chgtTypeByEnv;
    }
    public void setChgtTypeByEnv(EnumMap<EnvironmentType,String> _chgtTypeByEnv) {
        chgtTypeByEnv = _chgtTypeByEnv;
    }
    public ConstValuesType getConstValuesType() {
        return constValuesType;
    }
    public void setConstValuesType(ConstValuesType _constValuesType) {
        constValuesType = _constValuesType;
    }
    public ExchangeType getExchangeTypes() {
        return exchangeTypes;
    }
    public void setExchangeTypes(ExchangeType _exchangeTypes) {
        exchangeTypes = _exchangeTypes;
    }
    public StringList getConstTypes() {
        return constTypes;
    }
    public void setConstTypes(StringList _constTypes) {
        constTypes = _constTypes;
    }

    public StringList getAddedTypes() {
        return addedTypes;
    }

    public void setAddedTypes(StringList _addedTypes) {
        addedTypes = _addedTypes;
    }
}
