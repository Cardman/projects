package aiki.beans.fight;

import aiki.game.fight.StacksOfUses;
import code.bean.nat.NaNuSt;

public final class StacksOfUsesStruct extends NaNuSt {
    private final StacksOfUses inst;
    public StacksOfUsesStruct(StacksOfUses _instance) {
        inst=(_instance);
    }
    public StacksOfUses getStacksOfUses() {
        return inst;
    }
}
