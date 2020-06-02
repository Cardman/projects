package aiki.game.fight;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class TeamPosition implements Equallable<TeamPosition>, Displayable {

    private static final String EMPTY_STRING = "";
    private static final String INVALID=EMPTY_STRING;
    private static final char SEPARATOR=';';

    private final boolean valid;

    private final byte team;

    private final byte position;

    public TeamPosition() {
        valid = false;
        team = CustList.SIZE_EMPTY;
        position = CustList.SIZE_EMPTY;
    }
    public TeamPosition(byte _team, byte _position) {
        team = _team;
        position = _position;
        valid = _team != Fighter.BACK && _position != Fighter.BACK;
    }

    public TeamPosition(String _value) {
        if (StringList.quickEq(_value,INVALID)) {
            valid = false;
            team = CustList.SIZE_EMPTY;
            position = CustList.SIZE_EMPTY;
            return;
        }
        StringList elts_ = StringList.splitChars(_value, SEPARATOR);
        team = (byte) Numbers.parseInt(elts_.first());
        position = (byte) Numbers.parseInt(elts_.last());
        valid = true;
    }

    
    public static TeamPosition newTeamPosition(String _string) {
        return new TeamPosition(_string);
    }

    public boolean isValid() {
        return valid;
    }
    public static boolean eq(TeamPosition _tp1, TeamPosition _tp2) {
        return _tp1.eq(_tp2);
    }

    public byte getTeam() {
        return team;
    }

    public byte getPosition() {
        return position;
    }
    @Override
    public boolean eq(TeamPosition _g) {
        if (!Numbers.eq(getTeam(),_g.getTeam())) {
            return false;
        }
        if (!Numbers.eq(getPosition(),_g.getPosition())) {
            return false;
        }
        return true;
    }
    
    @Override
    public String display() {
        if (!isValid()) {
            return INVALID;
        }
        StringBuilder str_ = new StringBuilder();
        str_.append(team);
        str_.append(SEPARATOR);
        str_.append(position);
        return str_.toString();
    }
}
