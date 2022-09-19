package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.util.StringList;


public final class EffectProtectFromTypes extends Effect {

    private StringList immuAgainstTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkEmptyNotStringList(immuAgainstTypes,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),immuAgainstTypes,_data);
    }

    public StringList getImmuAgainstTypes() {
        return immuAgainstTypes;
    }

    public void setImmuAgainstTypes(StringList _immuAgainstTypes) {
        immuAgainstTypes = _immuAgainstTypes;
    }

}
