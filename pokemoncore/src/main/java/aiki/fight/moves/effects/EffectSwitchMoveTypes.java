package aiki.fight.moves.effects;

import aiki.db.DataBase;
import code.util.StringList;
import code.util.StringMap;


public final class EffectSwitchMoveTypes extends Effect {

    private StringMap<String> changeTypes;

    private StringList replacingTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (String k : changeTypes.getKeys()) {
            if (!_data.getTypes().containsObj(k)) {
                _data.setError(true);
                return;

            }
            if (!_data.getTypes().containsObj(changeTypes.getVal(k))) {
                _data.setError(true);
                return;

            }
        }
        if (!_data.getTypes().containsAllObj(replacingTypes)) {
            _data.setError(true);

        }
    }

    public StringMap<String> getChangeTypes() {
        return changeTypes;
    }

    public void setChangeTypes(StringMap<String> _changeTypes) {
        changeTypes = _changeTypes;
    }

    public StringList getReplacingTypes() {
        return replacingTypes;
    }

    public void setReplacingTypes(StringList _replacingTypes) {
        replacingTypes = _replacingTypes;
    }
}
