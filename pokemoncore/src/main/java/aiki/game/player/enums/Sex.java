package aiki.game.player.enums;
import aiki.map.pokemon.enums.Gender;
import code.util.StringList;

public enum Sex {
    GIRL(Gender.FEMALE),BOY(Gender.MALE);
    private final Gender gender;

    Sex(Gender _gender) {
        gender = _gender;
    }

    public Sex getOppositeSex() {
        if (StringList.quickEq(name(),GIRL.name())) {
            return BOY;
        }
        return GIRL;
    }

    public Gender getGender() {
        return gender;
    }
}
