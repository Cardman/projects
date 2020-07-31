package aiki.fight.pokemon.enums;
import aiki.map.pokemon.enums.Gender;
import code.util.EnumList;

public enum GenderRepartition {
    FEMALE,
    MALE,
    MIXED,
    LEGENDARY,
    NO_GENDER;

    public EnumList<Gender> getPossibleGenders() {
        if (this == FEMALE) {
            return new EnumList<Gender>(Gender.FEMALE);
        }
        if (this == MALE) {
            return new EnumList<Gender>(Gender.MALE);
        }
        if (this == MIXED) {
            return new EnumList<Gender>(Gender.FEMALE,Gender.MALE);
        }
        return new EnumList<Gender>(Gender.NO_GENDER);
    }

}
