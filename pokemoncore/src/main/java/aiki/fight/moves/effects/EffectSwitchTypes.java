package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.ConstValuesType;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.map.levels.enums.EnvironmentType;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.StringList;


public final class EffectSwitchTypes extends Effect {

    private EnumMap<EnvironmentType, String> chgtTypeByEnv;
    private ConstValuesType constValuesType;
    private ExchangeType exchangeTypes;
    private StringList constTypes;
    private StringList addedTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getTypes().containsAllObj(constTypes)) {
            _data.setError(true);
            return;

        }
        if (!_data.getTypes().containsAllObj(addedTypes)) {
            _data.setError(true);
            return;

        }
        if (!chgtTypeByEnv.isEmpty()) {
            if (constValuesType != ConstValuesType.NOTHING) {
                _data.setError(true);
                return;

            }
            for (EntryCust<EnvironmentType, String> e : chgtTypeByEnv
                    .entryList()) {
                if (!EnvironmentType.getEnvironments().containsObj(e.getKey())) {
                    _data.setError(true);
                    return;

                }
                if (!StringList.contains(_data.getTypes(), e.getValue())) {
                    _data.setError(true);
                    return;

                }
            }
            return;
        }
        if (exchangeTypes != ExchangeType.NOTHING) {
            if (constValuesType != ConstValuesType.NOTHING) {
                _data.setError(true);
                return;

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
                    _data.setError(true);
                    return;

                }
                return;
            }
            if (exchangeTypes == ExchangeType.GIVE_CONST) {
                if (constTypes.isEmpty()) {
                    _data.setError(true);
                    return;

                }
                return;
            }
            _data.setError(true);

        }
    }

    public EnumMap<EnvironmentType, String> getChgtTypeByEnv() {
        return chgtTypeByEnv;
    }

    public void setChgtTypeByEnv(EnumMap<EnvironmentType, String> _chgtTypeByEnv) {
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
