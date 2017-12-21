package aiki.game.player.enums;
import aiki.map.pokemon.enums.Gender;
import code.serialize.CheckedData;
import code.util.StringList;

@CheckedData
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
        if (StringList.quickEq(name(),BOY.name())) {
            return GIRL;
        }
        return null;
    }
    public static Sex getSexByName(String _env) {
        for (Sex e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return null;
    }
    public Gender getGender() {
        return gender;
    }
}
