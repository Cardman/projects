package aiki.map.pokemon.enums;
import code.util.EnumList;
import code.util.StringList;
import code.util.ints.Listable;

public enum Gender {
    FEMALE,MALE,NO_GENDER;
    public static EnumList<Gender> getGendersWithSex() {
        EnumList<Gender> genders_ = new EnumList<Gender>();
        genders_.add(FEMALE);
        genders_.add(MALE);
        return genders_;
    }
    public static Gender getGenderByName(String _env) {
        for (Gender e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return NO_GENDER;
    }
}
