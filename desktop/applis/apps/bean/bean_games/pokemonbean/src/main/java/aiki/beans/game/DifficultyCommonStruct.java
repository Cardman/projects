package aiki.beans.game;

import aiki.beans.DifficultyCommon;
import code.bean.nat.NaNuSt;

public final class DifficultyCommonStruct extends NaNuSt {
    private final DifficultyCommon inst;
    public DifficultyCommonStruct(DifficultyCommon _instance) {
        inst=(_instance);
    }
    public DifficultyCommon getDifficultyCommon() {
        return inst;
    }
}
