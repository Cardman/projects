package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.ConstValuesType;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.map.levels.enums.EnvironmentType;
import aiki.util.DataInfoChecker;
import code.util.AbsMap;
import code.util.StringList;


public final class EffectSwitchTypes extends Effect {

    private AbsMap<EnvironmentType, String> chgtTypeByEnv;
    private ConstValuesType constValuesType;
    private ExchangeType exchangeTypes;
    private StringList constTypes;
    private StringList addedTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),constTypes,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),addedTypes,_data);
        if (!chgtTypeByEnv.isEmpty()) {
            checkConstValuesType(_data);
            DataInfoChecker.checkStringListContains(_data.getTypes(),chgtTypeByEnv.values(),_data);
            return;
        }
        if (exchangeTypes != ExchangeType.NOTHING) {
            checkConstValuesType(_data);
            boolean checkTargetChoice_ = exchangeTypes == ExchangeType.GIVE_TO_TARGET || exchangeTypes == ExchangeType.GIVE_TO_THROWER || exchangeTypes == ExchangeType.EXCHANGE;
            if (checkTargetChoice_) {
                DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
                return;
            }
            DataInfoChecker.checkEmptyNotStringList(constTypes,_data);
        }
    }

    private void checkConstValuesType(DataBase _data) {
        if (constValuesType != ConstValuesType.NOTHING) {
            _data.setError(true);
        }
    }

    public AbsMap<EnvironmentType, String> getChgtTypeByEnv() {
        return chgtTypeByEnv;
    }

    public void setChgtTypeByEnv(AbsMap<EnvironmentType, String> _chgtTypeByEnv) {
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
