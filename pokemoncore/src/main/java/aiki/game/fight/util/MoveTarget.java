package aiki.game.fight.util;
import aiki.game.fight.TargetCoords;
import code.datacheck.CheckedData;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;

@CheckedData
public final class MoveTarget implements Equallable<MoveTarget> {

    private static final char SEPARATOR = ',';

    private String move;

    private TargetCoords target;

    public MoveTarget() {
    }

    public MoveTarget(String _str) {
        StringList list_ = StringList.splitChars(_str, SEPARATOR);
        move = list_.first();
        target = new TargetCoords(list_.last());
    }

    public MoveTarget(String _move, TargetCoords _target) {
        move = _move;
        target = _target;
    }

    @FromAndToString
    public static MoveTarget newMoveTarget(String _string) {
        return new MoveTarget(_string);
    }

    @Override
    public boolean eq(MoveTarget _obj) {
        if (!StringList.quickEq(move, _obj.getMove())) {
            return false;
        }
        if (!TargetCoords.eq(target, _obj.getTarget())) {
            return false;
        }
        return true;
    }

    @Override
    @FromAndToString
    public String toString() {
        return move+SEPARATOR+target;
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
}
