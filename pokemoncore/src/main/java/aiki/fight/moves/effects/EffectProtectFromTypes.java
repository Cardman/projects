package aiki.fight.moves.effects;

import aiki.db.DataBase;
import code.util.StringList;


public final class EffectProtectFromTypes extends Effect {

    private StringList immuAgainstTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (immuAgainstTypes.isEmpty()) {
            _data.setError(true);
        }
        if (!_data.getTypes().containsAllObj(immuAgainstTypes)) {
            _data.setError(true);
        }
    }

    public StringList getImmuAgainstTypes() {
        return immuAgainstTypes;
    }

    public void setImmuAgainstTypes(StringList _immuAgainstTypes) {
        immuAgainstTypes = _immuAgainstTypes;
    }

}
