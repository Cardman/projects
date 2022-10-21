package aiki.game.fight.util;
import aiki.util.TeamPositionList;

public final class PairListsFighters {

    private final TeamPositionList front;

    private final TeamPositionList back;

    public PairListsFighters(TeamPositionList _f, TeamPositionList _b) {
        front = _f;
        back = _b;
    }

    public TeamPositionList getFront() {
        return front;
    }

    public TeamPositionList getBack() {
        return back;
    }
}
