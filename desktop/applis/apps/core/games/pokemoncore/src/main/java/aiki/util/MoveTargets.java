package aiki.util;

import aiki.game.fight.Fighter;
import aiki.game.fight.TargetCoords;
import aiki.game.fight.util.MoveTarget;
import code.util.CollCapacity;

public final class MoveTargets extends CommonMap<MoveTarget,MoveTarget> {
    public MoveTargets() {

    }
    public MoveTargets(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected MoveTarget def() {
        return new MoveTarget("",new TargetCoords((short) -1, Fighter.BACK));
    }

    @Override
    protected boolean eq(MoveTarget _one, MoveTarget _two) {
        return _one.eq(_two);
    }

}
