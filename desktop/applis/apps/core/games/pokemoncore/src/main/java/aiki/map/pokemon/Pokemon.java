package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.map.pokemon.enums.Gender;


public abstract class Pokemon {

    /***/
    private String name;

    /***/
    private short level;

    /***/
    private Gender gender;

    /**non modifiable une fois affecte a l'objet.*/
    private String ability;

    /**si la chaine de caractere est vide alors le pokemon ne porte pas d'objet, sinon cette chaine vaut le nom de l'objet. */
    private String item;

    protected Pokemon() {
    }

    public boolean hasJustBeenCreated() {
        return getName().isEmpty();
    }

    public void validateAsNpc(DataBase _data) {
        if (getLevel() < _data.getMinLevel()) {
            _data.setError(true);
        }
        // level >= 1
        if (getLevel() > _data.getMaxLevel()) {
            _data.setError(true);
        }
        if (!_data.getPokedex().contains(getName())) {
            _data.setError(true);
        }
        if (!_data.getAbilities().contains(getAbility())) {
            _data.setError(true);
        }
        if (!getItem().isEmpty() && !_data.getItems().contains(getItem())) {
            _data.setError(true);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender _gender) {
        gender = _gender;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String _ability) {
        ability = _ability;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String _item) {
        item = _item;
    }

}
