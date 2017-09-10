package aiki.game.fight;
import code.serialize.CheckedData;
import code.util.StringList;
import code.util.annot.RwXml;
import code.util.ints.Equallable;
import code.util.ints.Listable;
import code.xml.FromAndToString;

@CheckedData
public final class MoveTeamPosition implements Equallable<MoveTeamPosition> {

    private static final char SEPARATOR=',';

    private final String move;

    private final TeamPosition teamPosition;

    public MoveTeamPosition(MoveTeamPosition _pair) {
        move = _pair.move;
        teamPosition = _pair.teamPosition;
    }

    public MoveTeamPosition(String _move, TeamPosition _cbt) {
        move = _move;
        teamPosition = _cbt;
    }

    @RwXml
    MoveTeamPosition(String _value) {
        StringList elts_ = StringList.splitChars(_value, SEPARATOR);
        move = elts_.first();
        teamPosition = new TeamPosition(elts_.last());
    }

    public static boolean equalsSet(Listable<MoveTeamPosition> _list1,Listable<MoveTeamPosition> _list2) {
        for (MoveTeamPosition a: _list2) {
            boolean contains_ = false;
            for (MoveTeamPosition b: _list1) {
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
        for (MoveTeamPosition a: _list1) {
            boolean contains_ = false;
            for (MoveTeamPosition b: _list2) {
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

    @FromAndToString
    public static MoveTeamPosition newMoveTeamPosition(String _string) {
        return new MoveTeamPosition(_string);
    }

    @Override
    public boolean eq(MoveTeamPosition _obj) {
        if (!StringList.quickEq(getMove(),_obj.getMove())) {
            return false;
        }
        if (!TeamPosition.eq(getTeamPosition(),_obj.getTeamPosition())) {
            return false;
        }
        return true;
    }

    @FromAndToString
    @Override
    public String toString() {
        return move+SEPARATOR+teamPosition;
    }

    public String getMove() {
        return move;
    }

    public TeamPosition getTeamPosition() {
        return teamPosition;
    }
}
