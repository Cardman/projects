package aiki.game.player.enums;
import aiki.db.*;
import aiki.facade.SexListInt;
import aiki.map.pokemon.enums.Gender;
import code.util.CustList;
import code.util.core.StringUtil;

public enum Sex {
    GIRL(Gender.FEMALE, DataBase.DEF_SEX_GIRL),BOY(Gender.MALE, DataBase.DEF_SEX_BOY),NO(Gender.NO_GENDER,DataBase.DEF_SEX_NO);
    private final Gender gender;
    private final String sexName;

    Sex(Gender _gender, String _s) {
        gender = _gender;
        sexName = _s;
    }

    public String getSexName() {
        return sexName;
    }
    public static Sex getSexByName(String _env, SexListInt _sexList) {
        CustList<Sex> values_ = _sexList.all();
        for (Sex e: values_) {
            if (StringUtil.quickEq(e.sexName, _env)) {
                return e;
            }
        }
        return values_.first();
    }
    public static CustList<Sex> all() {
        CustList<Sex> sexes_ = new CustList<Sex>();
        sexes_.add(GIRL);
        sexes_.add(BOY);
        return sexes_;
    }
    public Sex getOppositeSex() {
        if (this == NO) {
            return NO;
        }
        if (StringUtil.quickEq(sexName,GIRL.sexName)) {
            return BOY;
        }
        return GIRL;
    }

    public Gender getGender() {
        return gender;
    }
}
