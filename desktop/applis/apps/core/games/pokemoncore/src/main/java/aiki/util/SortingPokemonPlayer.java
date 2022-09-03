package aiki.util;
import aiki.facade.Sorting;
import aiki.map.pokemon.enums.Gender;

public final class SortingPokemonPlayer extends Sorting {

    private short level;

    private String ability;

    private String item;

    private String keyItem;

    private Gender gender;

    private short nbPossEvos;

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
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

    public String getKeyItem() {
        return keyItem;
    }

    public void setKeyItem(String _keyItem) {
        keyItem = _keyItem;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender _gender) {
        gender = _gender;
    }

    public short getNbPossEvos() {
        return nbPossEvos;
    }

    public void setNbPossEvos(short _nbPossEvos) {
        nbPossEvos = _nbPossEvos;
    }
}
