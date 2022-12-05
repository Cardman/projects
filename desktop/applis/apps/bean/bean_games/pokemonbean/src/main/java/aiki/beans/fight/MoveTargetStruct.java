package aiki.beans.fight;

import aiki.game.fight.util.MoveTarget;
import code.bean.nat.NaNuSt;

public final class MoveTargetStruct extends NaNuSt {
    private final MoveTarget inst;
    public MoveTargetStruct(MoveTarget _instance) {
        inst=(_instance);
    }
    public MoveTarget getMoveTarget() {
        return inst;
    }
}
