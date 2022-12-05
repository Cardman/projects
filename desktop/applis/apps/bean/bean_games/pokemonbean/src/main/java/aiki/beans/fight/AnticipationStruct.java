package aiki.beans.fight;

import aiki.game.fight.Anticipation;
import code.bean.nat.NaNuSt;

public final class AnticipationStruct extends NaNuSt {
    private final Anticipation inst;
    public AnticipationStruct(Anticipation _instance) {
        inst=(_instance);
    }
    public Anticipation getAnticipation() {
        return inst;
    }
}
