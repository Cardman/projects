package aiki.map.pokemon;
import aiki.db.DataBase;
import aiki.map.pokemon.enums.Gender;
import code.util.core.StringUtil;
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

    public static boolean eq(WildPk _wildOne, WildPk _wildTwo) {
        return _wildOne.eq(_wildTwo);
    }

    //For laws of apparition in areas
    @Override
    public boolean eq(WildPk _obj) {
        if (!StringUtil.quickEq(getName(),_obj.getName())) {
            return false;
        }
        if (getLevel() != _obj.getLevel()) {
            return false;
        }
        if (getGender() != _obj.getGender()) {
            return false;
        }
        if (!StringUtil.quickEq(getAbility(),_obj.getAbility())) {
            return false;
        }
        return StringUtil.quickEq(getItem(), _obj.getItem());
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

}
