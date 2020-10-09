package aiki.fight.moves.effects;

import aiki.db.DataBase;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;


public final class EffectSwitchMoveTypes extends Effect {

    private StringMap<String> changeTypes;

    private StringList replacingTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (String k : changeTypes.getKeys()) {
            if (!StringUtil.contains(_data.getTypes(), k)) {
                _data.setError(true);
            }
            if (!StringUtil.contains(_data.getTypes(), changeTypes.getVal(k))) {
                _data.setError(true);
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
