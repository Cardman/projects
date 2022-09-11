package aiki.game.fight;

import aiki.game.fight.actions.ActionMove;
import code.util.AbsComparerTreeMap;
import code.util.ints.Comparing;

public final class TeamPositionActionMoveMap extends AbsComparerTreeMap<TeamPosition, ActionMove> {
    public TeamPositionActionMoveMap(Comparing<TeamPosition> _cmp) {
        super(_cmp);
    }

}
