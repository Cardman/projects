package aiki.beans.fight;

import aiki.beans.facade.fight.MultPowerMoves;
import code.bean.nat.NaNuSt;

public final class MultPowerMovesStruct extends NaNuSt {
    private final MultPowerMoves inst;
    public MultPowerMovesStruct(MultPowerMoves _instance) {
        inst=(_instance);
    }
    public MultPowerMoves getMultPowerMoves() {
        return inst;
    }
}
