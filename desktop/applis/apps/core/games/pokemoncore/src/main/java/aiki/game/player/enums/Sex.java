package aiki.game.player.enums;
import aiki.map.pokemon.enums.Gender;
import code.util.core.StringUtil;

public enum Sex {
    GIRL(Gender.FEMALE, "GIRL"),BOY(Gender.MALE, "BOY");
    private final Gender gender;
    private final String sexName;

    Sex(Gender _gender, String _s) {
        gender = _gender;
        sexName = _s;
    }

    public String getSexName() {
        return sexName;
    }

    public Sex getOppositeSex() {
        if (StringUtil.quickEq(sexName,GIRL.sexName)) {
            return BOY;
        }
        return GIRL;
    }

    public Gender getGender() {
        return gender;
    }
}
