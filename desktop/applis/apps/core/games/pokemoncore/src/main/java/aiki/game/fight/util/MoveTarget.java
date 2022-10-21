package aiki.game.fight.util;

import aiki.db.DataBase;
import aiki.game.fight.TargetCoords;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MoveTarget {

    private static final char SEPARATOR = ',';

    private final String move;

    private final TargetCoords target;

    public MoveTarget(String _str) {
        StringList list_ = StringUtil.splitChars(_str, SEPARATOR);
        move = list_.first();
        target = new TargetCoords(list_.last());
    }

    public MoveTarget(String _move, TargetCoords _target) {
        move = _move;
        target = _target;
    }
    public static MoveTarget def() {
        return new MoveTarget(DataBase.EMPTY_STRING,TargetCoords.def());
    }
    public static MoveTarget newMoveTarget(String _string) {
        return new MoveTarget(_string);
    }

    public boolean eq(MoveTarget _obj) {
        if (!StringUtil.quickEq(move, _obj.getMove())) {
            return false;
        }
        return TargetCoords.eq(target, _obj.getTarget());
    }

    public String getMove() {
        return move;
    }

    public TargetCoords getTarget() {
        return target;
    }

    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(move);
        str_.append(SEPARATOR);
        str_.append(target.display());
        return str_.toString();
    }
}
