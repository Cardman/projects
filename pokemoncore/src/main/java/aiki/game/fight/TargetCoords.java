package aiki.game.fight;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class TargetCoords implements Equallable<TargetCoords>, Displayable {

    private static final char SEPARATOR=';';

    private final short team;

    private final short position;

    public TargetCoords() {
        team = CustList.SIZE_EMPTY;
        position = CustList.SIZE_EMPTY;
    }

    public TargetCoords(short _team,short _position) {
        team = _team;
        position = _position;
    }

    public TargetCoords(String _value) {
        StringList elts_ = StringList.splitChars(_value, SEPARATOR);
        team = (short) Numbers.parseInt(elts_.first());
        position = (short) Numbers.parseInt(elts_.last());
    }

    
    public static TargetCoords newTargetCoords(String _string) {
        return new TargetCoords(_string);
    }

    public static TargetCoords toUserTarget(short _position) {
        return new TargetCoords(Fight.PLAYER, _position);
    }

    public static TargetCoords toFoeTarget(short _position) {
        return new TargetCoords(Fight.FOE, _position);
    }

    public static boolean eq(TargetCoords _tp1, TargetCoords _tp2) {
        return _tp1.eq(_tp2);
    }

    @Override
    public boolean eq(TargetCoords _obj) {
        if (!Numbers.eq(getTeam(),_obj.getTeam())) {
            return false;
        }
        if (!Numbers.eq(getPosition(),_obj.getPosition())) {
            return false;
        }
        return true;
    }

    public short getTeam() {
        return team;
    }

    public short getPosition() {
        return position;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(team);
        str_.append(SEPARATOR);
        str_.append(position);
        return str_.toString();
    }
}
