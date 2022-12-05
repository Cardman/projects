package aiki.beans.fight;

import aiki.game.fight.TargetCoords;
import code.bean.nat.NaNuSt;

public final class TargetCoordsStruct extends NaNuSt {
    private final TargetCoords inst;
    public TargetCoordsStruct(TargetCoords _instance) {
        inst=(_instance);
    }
    public TargetCoords getTargetCoords() {
        return inst;
    }
}
