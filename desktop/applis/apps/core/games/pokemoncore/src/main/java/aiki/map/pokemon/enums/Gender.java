package aiki.map.pokemon.enums;

import code.util.CustList;
import code.util.IdList;
import code.util.core.StringUtil;

public enum Gender {
    FEMALE("FEMALE"),MALE("MALE"),NO_GENDER("NO_GENDER");
    private final String genderName;
    Gender(String _g) {
        genderName = _g;
    }

    public String getGenderName() {
        return genderName;
    }
    public static Gender getGenderByName(String _env) {
        for (Gender e: Gender.all()) {
            if (StringUtil.quickEq(e.genderName, _env)) {
                return e;
            }
        }
        return Gender.NO_GENDER;
    }
    public static CustList<Gender> all() {
        CustList<Gender> genders_ = new CustList<Gender>();
        genders_.add(FEMALE);
        genders_.add(MALE);
        genders_.add(NO_GENDER);
        return genders_;
    }

    public static IdList<Gender> getGendersWithSex() {
        IdList<Gender> genders_ = new IdList<Gender>();
        genders_.add(FEMALE);
        genders_.add(MALE);
        return genders_;
    }
}
