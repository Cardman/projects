package aiki.game.fight.util;
import aiki.game.fight.TeamPosition;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

@CheckedData
public final class NbEffectFighterCoords implements Equallable<NbEffectFighterCoords>, Displayable {

    private static final char SEPARATOR = ',';

    private final int number;

    private final TeamPosition position;

    public NbEffectFighterCoords(String _str) {
        StringList list_ = StringList.splitChars(_str, SEPARATOR);
        number = Integer.parseInt(list_.first());
        position = new TeamPosition(list_.last());
    }

    public NbEffectFighterCoords(int _number, TeamPosition _position) {
        number = _number;
        position = _position;
    }

    @FromAndToString
    public static NbEffectFighterCoords newNbEffectFighterCoords(String _string) {
        return new NbEffectFighterCoords(_string);
    }

    @Override
    public boolean eq(NbEffectFighterCoords _obj) {
        if (!Numbers.eq(number, _obj.getNumber())) {
            return false;
        }
        if (!TeamPosition.eq(position, _obj.getPosition())) {
            return false;
        }
        return true;
    }

    public int getNumber() {
        return number;
    }

    public TeamPosition getPosition() {
        return position;
    }

    @Override
    @FromAndToString
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(number);
        str_.append(SEPARATOR);
        str_.append(position.display());
        return str_.toString();
    }
}
