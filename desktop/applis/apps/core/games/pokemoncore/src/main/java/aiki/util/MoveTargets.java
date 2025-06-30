package aiki.util;

import aiki.game.fight.Fighter;
import aiki.game.fight.TargetCoords;
import aiki.game.fight.util.MoveTarget;
import code.util.*;

public final class MoveTargets extends MoveTargetsParam<MoveTarget> {
    public MoveTargets() {

    }
    public MoveTargets(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected MoveTarget def() {
        return new MoveTarget("",new TargetCoords(-1, Fighter.BACK));
    }

}
