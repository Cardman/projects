package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.map.pokemon.enums.Gender;
import code.util.core.StringUtil;


public final class WildPk extends Pokemon {


    public WildPk() {
        setName(DataBase.EMPTY_STRING);
        setLevel((short) 1);
        setGender(Gender.NO_GENDER);
        setAbility(DataBase.EMPTY_STRING);
        setItem(DataBase.EMPTY_STRING);
    }

    public static boolean eq(WildPk _wildOne, WildPk _wildTwo) {
        return _wildOne.eq(_wildTwo);
    }

    //For laws of apparition in areas
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


}
