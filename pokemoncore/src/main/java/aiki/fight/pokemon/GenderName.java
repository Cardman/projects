package aiki.fight.pokemon;
import code.util.StringList;
import code.util.ints.Equallable;
import aiki.map.pokemon.enums.Gender;

public final class GenderName implements Equallable<GenderName> {

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

    @Override
    public boolean eq(GenderName _g) {
        if (gender != _g.gender) {
            return false;
        }
        if (!StringList.quickEq(name, _g.name)) {
            return false;
        }
        return true;
    }
}
