package aiki.beans.fight;

import aiki.game.fight.util.CopiedMove;
import code.bean.nat.NaNuSt;

public final class CopiedMoveStruct extends NaNuSt {
    private final CopiedMove inst;
    public CopiedMoveStruct(CopiedMove _instance) {
        inst=(_instance);
    }
    public CopiedMove getCopiedMove() {
        return inst;
    }
}
