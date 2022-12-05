package aiki.beans;

import aiki.fight.util.LevelMove;
import code.bean.nat.NaNuSt;

public final class LevelMoveStruct extends NaNuSt {
    private final LevelMove inst;
    public LevelMoveStruct(LevelMove _instance) {
        inst=(_instance);
    }
    public LevelMove getLevelMove() {
        return inst;
    }
}
