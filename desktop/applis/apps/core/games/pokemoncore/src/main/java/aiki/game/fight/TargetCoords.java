package aiki.game.fight;

import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class TargetCoords {

    private static final char SEPARATOR=';';

    private static final int DISABLED = -1;
    private final int team;

    private final int position;

    public TargetCoords(int _team,int _position) {
        team = _team;
        position = _position;
    }

    public TargetCoords(String _value) {
        StringList elts_ = StringUtil.splitChars(_value, SEPARATOR);
        team = NumberUtil.parseInt(elts_.first());
        position = NumberUtil.parseInt(elts_.last());
    }
    public static TargetCoords def() {
        return new TargetCoords(DISABLED,Fighter.BACK);
    }

    public static boolean ko(TargetCoords _pos) {
        return koTeam(_pos.getTeam()) ||koPosition(_pos.getPosition());
    }

    public static boolean koTeam(int _team) {
        return !NumberUtil.eq(_team, Fight.CST_PLAYER) && !NumberUtil.eq(_team, Fight.CST_FOE);
    }

    public static boolean koPosition(int _pos) {
        return !NumberUtil.eq(_pos, Fighter.BACK) && _pos < 0;
    }
    
    public static TargetCoords newTargetCoords(String _string) {
        return new TargetCoords(_string);
    }

    public static TargetCoords toUserTarget(int _position) {
        return new TargetCoords(Fight.CST_PLAYER, _position);
    }

    public static TargetCoords toFoeTarget(int _position) {
        return new TargetCoords(Fight.CST_FOE, _position);
    }

    public static boolean eq(TargetCoords _tp1, TargetCoords _tp2) {
        return _tp1.eq(_tp2);
    }

    public boolean eq(TargetCoords _obj) {
        if (!NumberUtil.eq(getTeam(),_obj.getTeam())) {
            return false;
        }
        return NumberUtil.eq(getPosition(), _obj.getPosition());
    }

    public boolean isEnabled() {
        return getTeam() != DISABLED;
    }
    public int getTeam() {
        return team;
    }

    public int getPosition() {
        return position;
    }

    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(team);
        str_.append(SEPARATOR);
        str_.append(position);
        return str_.toString();
    }
}
