package aiki.fight.pokemon.enums;
import aiki.map.pokemon.enums.Gender;
import code.serialize.CheckedData;
import code.util.EnumList;

@CheckedData
public enum GenderRepartition {
    FEMALE(Gender.FEMALE),
    MALE(Gender.MALE),
    MIXED(Gender.FEMALE,Gender.MALE),
    LEGENDARY(Gender.NO_GENDER),
    NO_GENDER(Gender.NO_GENDER);
    private final EnumList<Gender> possibleGenders = new EnumList<Gender>();

    GenderRepartition(Gender... _genders) {
        for (Gender g: _genders) {
            possibleGenders.add(g);
        }
    }

    public EnumList<Gender> getPossibleGenders() {
        return new EnumList<Gender>(possibleGenders);
    }

}
