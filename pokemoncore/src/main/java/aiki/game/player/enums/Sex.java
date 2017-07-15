package aiki.game.player.enums;
import aiki.map.pokemon.enums.Gender;
import code.datacheck.CheckedData;
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

    public Gender getGender() {
        return gender;
    }
}
