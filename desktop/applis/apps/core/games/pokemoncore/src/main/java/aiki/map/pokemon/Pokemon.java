package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.map.pokemon.enums.Gender;
import aiki.util.DataInfoChecker;


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
        DataInfoChecker.checkLower(_data.getMinLevel(),getLevel(),_data);
        DataInfoChecker.checkGreater(_data.getMaxLevel(),getLevel(),_data);
        // level >= 1
        DataInfoChecker.checkStringListContains(_data.getPokedex().getKeys(),getName(),_data);
        DataInfoChecker.checkStringListContains(_data.getAbilities().getKeys(),getAbility(),_data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getItems().getKeys(),getItem(),_data);
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
