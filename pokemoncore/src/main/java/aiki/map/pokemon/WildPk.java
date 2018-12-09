package aiki.map.pokemon;
import aiki.DataBase;
import aiki.map.pokemon.enums.Gender;
import code.util.StringList;
import code.util.ints.Equallable;


public final class WildPk extends Pokemon implements Equallable<WildPk> {

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

    public WildPk() {
        name = DataBase.EMPTY_STRING;
        level = 1;
        gender = Gender.NO_GENDER;
        ability = DataBase.EMPTY_STRING;
        item = DataBase.EMPTY_STRING;
    }

//    public WildPk(Pokemon _pk) {
////        super(_pk);
//        name = _pk.getName();
//        level = _pk.getLevel();
//        gender = _pk.getGender();
//        ability = _pk.getAbility();
//        item = _pk.getItem();
//    }

    public static boolean eq(WildPk _wildOne, WildPk _wildTwo) {
        if (_wildOne == null) {
            return _wildTwo == null;
        }
        return _wildOne.eq(_wildTwo);
    }

    //For laws of apparition in areas
    @Override
    public boolean eq(WildPk _obj) {
        if (!StringList.quickEq(getName(),_obj.getName())) {
            return false;
        }
        if (getLevel() != _obj.getLevel()) {
            return false;
        }
        if (getGender() != _obj.getGender()) {
            return false;
        }
        if (!StringList.quickEq(getAbility(),_obj.getAbility())) {
            return false;
        }
        if (!StringList.quickEq(getItem(),_obj.getItem())) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String _name) {
        name = _name;
    }

    @Override
    public short getLevel() {
        return level;
    }

    @Override
    public void setLevel(short _level) {
        level = _level;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender _gender) {
        gender = _gender;
    }

    @Override
    public String getAbility() {
        return ability;
    }

    @Override
    public void setAbility(String _ability) {
        ability = _ability;
    }

    @Override
    public String getItem() {
        return item;
    }

    @Override
    public void setItem(String _item) {
        item = _item;
    }

//    @Override
//    public void beforeSave() {
//        name = getName();
//        level = getLevel();
//        ability = getAbility();
//        gender = getGender();
//        item = getItem();
//    }
//
//    @Override
//    public void afterLoad() {
//    }
}
