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
    public static Sex getSexByName(String _env) {
        Sex[] values_ = values();
        for (Sex e: values_) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return values_[0];
    }
    public Gender getGender() {
        return gender;
    }
}
