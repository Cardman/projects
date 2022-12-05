package aiki.beans.fight;

import aiki.beans.facade.fight.KeyHypothesis;
import code.bean.nat.NaNuSt;

public final class KeyHypothesisStruct extends NaNuSt {
    private final KeyHypothesis inst;
    public KeyHypothesisStruct(KeyHypothesis _instance) {
        inst=(_instance);
    }
    public KeyHypothesis getKeyHypothesis() {
        return inst;
    }
}
