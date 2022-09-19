package aiki.fight.pokemon;

import aiki.map.pokemon.enums.Gender;

public final class GenderName {

    private final Gender gender;

    private final String name;

    public GenderName(Gender _gender, String _name) {
        gender = _gender;
        name = _name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

}
