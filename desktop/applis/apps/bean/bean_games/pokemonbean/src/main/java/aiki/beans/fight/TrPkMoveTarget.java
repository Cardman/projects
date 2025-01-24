package aiki.beans.fight;

import aiki.game.fight.util.*;

public final class TrPkMoveTarget {
    private final MoveTarget moveTarget;
    private final String translation;
    private final int index;

    public TrPkMoveTarget(MoveTarget _m, String _t) {
        this(_m,_t,0);
    }
    public TrPkMoveTarget(MoveTarget _m, String _t, int _i) {
        this.moveTarget = _m;
        this.translation = _t;
        this.index = _i;
    }

    public MoveTarget getMoveTarget() {
        return moveTarget;
    }

    public String getTranslation() {
        return translation;
    }

    public int getIndex() {
        return index;
    }
}
