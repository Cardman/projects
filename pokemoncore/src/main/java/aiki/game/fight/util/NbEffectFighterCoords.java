package aiki.game.fight.util;
import aiki.game.fight.TeamPosition;
import code.serialize.CheckedData;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;

@CheckedData
public final class NbEffectFighterCoords implements Equallable<NbEffectFighterCoords> {

    private static final char SEPARATOR = ',';

    private static final String EMPTY_STRING = "";

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
        if (!(_obj instanceof NbEffectFighterCoords)) {
            return false;
        }
        if (!Numbers.eq(number, _obj.getNumber())) {
            return false;
        }
        if (!TeamPosition.eq(position, _obj.getPosition())) {
            return false;
        }
        return true;
    }

    @Override
    @FromAndToString
    public String toString() {
        return EMPTY_STRING+number+SEPARATOR+position;
    }

    public int getNumber() {
        return number;
    }

    public TeamPosition getPosition() {
        return position;
    }
}
