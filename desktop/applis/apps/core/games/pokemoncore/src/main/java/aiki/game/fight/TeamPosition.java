package aiki.game.fight;

import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class TeamPosition {

    private static final char SEPARATOR=';';
    private static final String EMPTY_STRING = "";
    private static final String INVALID=EMPTY_STRING;

    private final boolean valid;

    private final int team;

    private final int position;

    public TeamPosition() {
        valid = false;
        team = IndexConstants.SIZE_EMPTY;
        position = IndexConstants.SIZE_EMPTY;
    }
    public TeamPosition(int _team, int _position) {
        team = _team;
        position = _position;
        valid = _team != Fighter.BACK && _position != Fighter.BACK;
    }

    public TeamPosition(String _value) {
        if (StringUtil.quickEq(_value,INVALID)) {
            valid = false;
            team = IndexConstants.SIZE_EMPTY;
            position = IndexConstants.SIZE_EMPTY;
            return;
        }
        StringList elts_ = StringUtil.splitChars(_value, SEPARATOR);
        team = NumberUtil.parseInt(elts_.first());
        position = NumberUtil.parseInt(elts_.last());
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

    public int getTeam() {
        return team;
    }

    public int getPosition() {
        return position;
    }

    public boolean eq(TeamPosition _g) {
        if (!NumberUtil.eq(getTeam(),_g.getTeam())) {
            return false;
        }
        return NumberUtil.eq(getPosition(), _g.getPosition());
    }

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
