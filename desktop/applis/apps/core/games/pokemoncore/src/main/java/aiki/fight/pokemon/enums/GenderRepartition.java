package aiki.fight.pokemon.enums;
import aiki.db.*;
import aiki.map.pokemon.enums.Gender;
import code.util.CustList;
import code.util.IdList;
import code.util.core.StringUtil;

public enum GenderRepartition {
    FEMALE(DataBase.DEF_GENDER_REP_FEMALE),
    MALE(DataBase.DEF_GENDER_REP_MALE),
    MIXED(DataBase.DEF_GENDER_REP_MIXED),
    LEGENDARY(DataBase.DEF_GENDER_REP_LEGENDARY),
    NO_GENDER(DataBase.DEF_GENDER_REP_NO_GENDER);
    private final String genderRep;
    GenderRepartition(String _g) {
        genderRep = _g;
    }

    public String getGenderRep() {
        return genderRep;
    }

    public static GenderRepartition getGenderRepartitionByName(String _env) {
        for (GenderRepartition e: all()) {
            if (StringUtil.quickEq(e.getGenderRep(),_env)) {
                return e;
            }
        }
        return GenderRepartition.NO_GENDER;
    }
    public static CustList<GenderRepartition> all() {
        CustList<GenderRepartition> genders_ = new CustList<GenderRepartition>();
        genders_.add(FEMALE);
        genders_.add(MALE);
        genders_.add(MIXED);
        genders_.add(LEGENDARY);
        genders_.add(NO_GENDER);
        return genders_;
    }
    public IdList<Gender> getPossibleGenders() {
        if (this == FEMALE) {
            return new IdList<Gender>(Gender.FEMALE);
        }
        if (this == MALE) {
            return new IdList<Gender>(Gender.MALE);
        }
        if (this == MIXED) {
            return new IdList<Gender>(Gender.FEMALE,Gender.MALE);
        }
        return new IdList<Gender>(Gender.NO_GENDER);
    }

}
