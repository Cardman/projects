package aiki.game.fight.util;
import aiki.game.fight.TargetCoords;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class MoveTarget implements Equallable<MoveTarget>, Displayable {

    private static final char SEPARATOR = ',';

    private String move;

    private TargetCoords target;

    public MoveTarget() {
    }

    public MoveTarget(String _str) {
        StringList list_ = StringUtil.splitChars(_str, SEPARATOR);
        move = list_.first();
        target = new TargetCoords(list_.last());
    }

    public MoveTarget(String _move, TargetCoords _target) {
        setMove(_move);
        setTarget(_target);
    }
    
    public static MoveTarget newMoveTarget(String _string) {
        return new MoveTarget(_string);
    }

    @Override
    public boolean eq(MoveTarget _obj) {
        if (!StringUtil.quickEq(move, _obj.getMove())) {
            return false;
        }
        return TargetCoords.eq(target, _obj.getTarget());
    }

    public String getMove() {
        return move;
    }

    public void setMove(String _move) {
        move = _move;
    }

    public TargetCoords getTarget() {
        return target;
    }

    public void setTarget(TargetCoords _target) {
        target = _target;
    }

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(move);
        str_.append(SEPARATOR);
        str_.append(target.display());
        return str_.toString();
    }
}
