package aiki.fight.pokemon.enums;
import aiki.map.pokemon.enums.Gender;
import code.util.IdList;

public enum GenderRepartition {
    FEMALE,
    MALE,
    MIXED,
    LEGENDARY,
    NO_GENDER;

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
