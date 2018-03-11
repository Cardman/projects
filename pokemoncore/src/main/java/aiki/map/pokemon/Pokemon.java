package aiki.map.pokemon;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.pokemon.enums.Gender;
import code.util.annot.RwXml;

@RwXml
public abstract class Pokemon {

    protected Pokemon() {
    }

    public boolean hasJustBeenCreated() {
        return getName().isEmpty() && getAbility().isEmpty();
    }
    public void validate(DataBase _data, boolean _ref) {
        if (!_ref) {
            if (hasJustBeenCreated()) {
                return;
            }
        }
        if (getLevel() < _data.getMinLevel()) {
            throw new DataException();
        }
        //level >= 1
        if (getLevel() > _data.getMaxLevel()) {
            throw new DataException();
        }
        if (!_data.getPokedex().contains(getName())) {
            throw new DataException();
        }
        if (!_data.getAbilities().contains(getAbility())) {
            throw new DataException();
        }
        if (!getItem().isEmpty()) {
            if (!_data.getItems().contains(getItem())) {
                throw new DataException();
            }
        }
    }

    public abstract String getName();

    public abstract void setName(String _name);

    public abstract short getLevel();

    public abstract void setLevel(short _level);

    public abstract Gender getGender();

    public abstract void setGender(Gender _gender);

    public abstract String getAbility();

    public abstract void setAbility(String _ability);

    public abstract String getItem();

    public abstract void setItem(String _item);
}
