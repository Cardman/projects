package aiki.game.fight.util;
import aiki.game.fight.ActivityOfMove;
import code.datacheck.CheckedData;
import code.util.StringList;
import code.xml.FromAndToString;

@CheckedData
public final class AffectedMove {

    private static final char SEPARATOR='/';

    private String move;

    private final ActivityOfMove activity;

    public AffectedMove(String _string) {
        StringList elts_ = StringList.splitChars(_string, SEPARATOR);
        move = elts_.first();
        activity = new ActivityOfMove(elts_.last());
    }

    public AffectedMove(String _move, ActivityOfMove _activity) {
        move = _move;
        activity = _activity;
    }

    @FromAndToString
    public static AffectedMove newAffectedMove(String _string) {
        return new AffectedMove(_string);
    }

    @FromAndToString
    @Override
    public String toString() {
        return move+SEPARATOR+activity;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String _move) {
        move = _move;
    }

    public ActivityOfMove getActivity() {
        return activity;
    }
}
