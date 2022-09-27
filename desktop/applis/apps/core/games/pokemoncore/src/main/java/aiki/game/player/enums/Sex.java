package aiki.game.player.enums;
import aiki.map.pokemon.enums.Gender;
import code.util.CustList;
import code.util.core.StringUtil;

public enum Sex {
    GIRL(Gender.FEMALE, "GIRL"),BOY(Gender.MALE, "BOY");
    private final Gender gender;
    private final String sexName;

    Sex(Gender _gender, String _s) {
        gender = _gender;
        sexName = _s;
    }

    public String getSexName() {
        return sexName;
    }
    public static Sex getSexByName(String _env) {
        CustList<Sex> values_ = Sex.all();
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
        if (StringUtil.quickEq(sexName,GIRL.sexName)) {
            return BOY;
        }
        return GIRL;
    }

    public Gender getGender() {
        return gender;
    }
}
