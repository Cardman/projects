package aiki.beans.fight;

import aiki.game.fight.util.*;

public final class TrPkMoveTarget {
    private final MoveTarget moveTarget;
    private final String translation;
    private final int index;
    private final boolean chosen;

    public TrPkMoveTarget(MoveTarget _m, String _t) {
        this(_m,_t,0);
    }

    public TrPkMoveTarget(MoveTarget _m, String _t, int _i) {
        this(_m,_t,_i, false);
    }
    public TrPkMoveTarget(MoveTarget _m, String _t, int _i, boolean _ch) {
        this.moveTarget = _m;
        this.translation = _t;
        this.index = _i;
        this.chosen = _ch;
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

    public boolean isChosen() {
        return chosen;
    }
}
