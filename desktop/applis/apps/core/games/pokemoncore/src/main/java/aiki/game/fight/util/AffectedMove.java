package aiki.game.fight.util;
import aiki.game.fight.ActivityOfMove;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class AffectedMove implements Displayable {

    private static final char SEPARATOR='/';

    private String move;

    private final ActivityOfMove activity;

    public AffectedMove(String _string) {
        StringList elts_ = StringUtil.splitChars(_string, SEPARATOR);
        move = elts_.first();
        activity = new ActivityOfMove(elts_.last());
    }

    public AffectedMove(String _move, ActivityOfMove _activity) {
        setMove(_move);
        activity = _activity;
    }

    
    public static AffectedMove newAffectedMove(String _string) {
        return new AffectedMove(_string);
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

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(move);
        str_.append(SEPARATOR);
        str_.append(activity.display());
        return str_.toString();
    }
}
