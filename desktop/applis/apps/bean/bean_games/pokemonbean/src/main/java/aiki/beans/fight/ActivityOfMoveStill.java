package aiki.beans.fight;

import aiki.game.fight.*;

public final class ActivityOfMoveStill {
    private final ActivityOfMove activity;
    private final boolean still;

    public ActivityOfMoveStill(ActivityOfMove _a) {
        this(_a,false);
    }

    public ActivityOfMoveStill(ActivityOfMove _a, boolean _s) {
        this.activity = _a;
        this.still = _s;
    }

    public ActivityOfMove getActivity() {
        return activity;
    }

    public boolean isStill() {
        return still;
    }
}
