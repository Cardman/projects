package aiki.beans.fight;

import aiki.game.fight.util.AffectedMove;
import code.bean.nat.NaNuSt;

public final class AffectedMoveStruct extends NaNuSt {
    private final AffectedMove inst;
    public AffectedMoveStruct(AffectedMove _instance) {
        inst=(_instance);
    }
    public AffectedMove getAffectedMove() {
        return inst;
    }
}
