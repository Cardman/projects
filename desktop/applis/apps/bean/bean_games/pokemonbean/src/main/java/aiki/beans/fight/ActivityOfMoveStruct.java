package aiki.beans.fight;

import aiki.game.fight.ActivityOfMove;
import code.bean.nat.NaNuSt;

public final class ActivityOfMoveStruct extends NaNuSt {
    private final ActivityOfMove inst;
    public ActivityOfMoveStruct(ActivityOfMove _instance) {
        inst=(_instance);
    }
    public ActivityOfMove getActivityOfMove() {
        return inst;
    }
}
