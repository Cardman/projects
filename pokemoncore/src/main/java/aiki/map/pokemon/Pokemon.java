package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.map.pokemon.enums.Gender;


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
            _data.setError(true);
            return;

        }
        // level >= 1
        if (getLevel() > _data.getMaxLevel()) {
            _data.setError(true);
            return;

        }
        if (!_data.getPokedex().contains(getName())) {
            _data.setError(true);
            return;

        }
        if (!_data.getAbilities().contains(getAbility())) {
            _data.setError(true);
            return;

        }
        if (!getItem().isEmpty()) {
            if (!_data.getItems().contains(getItem())) {
                _data.setError(true);
                return;

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
