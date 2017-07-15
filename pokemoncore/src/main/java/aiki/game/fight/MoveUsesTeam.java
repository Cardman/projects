package aiki.game.fight;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Equallable;

public final class MoveUsesTeam implements Equallable<MoveUsesTeam> {

    private final String move;

    private final byte nbUses;

    public MoveUsesTeam(String _move, byte _nbUses) {
        move = _move;
        nbUses = _nbUses;
    }
    //TODO null check
    public static boolean equalsSet(EqList<MoveUsesTeam> _list1,EqList<MoveUsesTeam> _list2) {
        for (MoveUsesTeam a: _list2) {
            boolean contains_ = false;
            for (MoveUsesTeam b: _list1) {
                if (a == null) {
                    if (b == null) {
                        contains_ = true;
                        break;
                    }
                    continue;
                }
                if (a.eq(b)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (MoveUsesTeam a: _list1) {
            boolean contains_ = false;
            for (MoveUsesTeam b: _list2) {
                if (a == null) {
                    if (b == null) {
                        contains_ = true;
                        break;
                    }
                    continue;
                }
                if (a.eq(b)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean eq(MoveUsesTeam _obj) {
        if (!StringList.quickEq(move, _obj.move)) {
            return false;
        }
        if (!Numbers.eq(nbUses, _obj.nbUses)) {
            return false;
        }
        return true;
    }
}
