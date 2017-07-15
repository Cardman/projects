package aiki.fight.moves.effects;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;
import aiki.DataBase;
import aiki.exceptions.DataException;

@RwXml
public class EffectSwitchMoveTypes extends Effect {

    private StringMap<String> changeTypes;

    private StringList replacingTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (String k: changeTypes.getKeys()) {
            if (!_data.getTypes().containsObj(k)) {
                throw new DataException();
            }
            if (!_data.getTypes().containsObj(changeTypes.getVal(k))) {
                throw new DataException();
            }
        }
        if (!_data.getTypes().containsAllObj(replacingTypes)) {
            throw new DataException();
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
