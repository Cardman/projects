package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.util.StringList;
import code.util.StringMap;


public final class EffectSwitchMoveTypes extends Effect {

    private StringMap<String> changeTypes;

    private StringList replacingTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),changeTypes.getKeys(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),changeTypes.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),replacingTypes,_data);
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
