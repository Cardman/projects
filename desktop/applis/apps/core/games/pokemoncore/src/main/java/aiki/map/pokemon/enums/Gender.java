package aiki.map.pokemon.enums;
import code.util.EnumList;

public enum Gender {
    FEMALE("FEMALE"),MALE("MALE"),NO_GENDER("NO_GENDER");
    private final String genderName;
    Gender(String _g) {
        genderName = _g;
    }

    public String getGenderName() {
        return genderName;
    }

    public static EnumList<Gender> getGendersWithSex() {
        EnumList<Gender> genders_ = new EnumList<Gender>();
        genders_.add(FEMALE);
        genders_.add(MALE);
        return genders_;
    }
}
