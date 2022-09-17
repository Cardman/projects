package aiki.game.fight.util;
import aiki.game.fight.TeamPosition;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class NbEffectFighterCoords {

    private static final char SEPARATOR = ',';

    private final int number;

    private final TeamPosition position;

    public NbEffectFighterCoords(String _str) {
        StringList list_ = StringUtil.splitChars(_str, SEPARATOR);
        number = NumberUtil.parseInt(list_.first());
        position = new TeamPosition(list_.last());
    }

    public NbEffectFighterCoords(int _number, TeamPosition _position) {
        number = _number;
        position = _position;
    }

    
    public static NbEffectFighterCoords newNbEffectFighterCoords(String _string) {
        return new NbEffectFighterCoords(_string);
    }

    public boolean eq(NbEffectFighterCoords _obj) {
        if (!NumberUtil.eq(number, _obj.getNumber())) {
            return false;
        }
        return TeamPosition.eq(position, _obj.getPosition());
    }

    public int getNumber() {
        return number;
    }

    public TeamPosition getPosition() {
        return position;
    }

    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(number);
        str_.append(SEPARATOR);
        str_.append(position.display());
        return str_.toString();
    }
}
