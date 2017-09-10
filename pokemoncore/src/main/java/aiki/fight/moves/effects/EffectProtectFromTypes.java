package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public class EffectProtectFromTypes extends Effect {

    private StringList immuAgainstTypes;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (immuAgainstTypes.isEmpty()) {
            throw new DataException();
        }
        if (!_data.getTypes().containsAllObj(immuAgainstTypes)) {
            throw new DataException();
        }
    }

    public StringList getImmuAgainstTypes() {
        return immuAgainstTypes;
    }

    public void setImmuAgainstTypes(StringList _immuAgainstTypes) {
        immuAgainstTypes = _immuAgainstTypes;
    }

}
