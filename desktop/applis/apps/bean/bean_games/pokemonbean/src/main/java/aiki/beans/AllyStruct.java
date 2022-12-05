package aiki.beans;

import aiki.map.characters.Ally;
import code.bean.nat.NaNuSt;

public final class AllyStruct extends NaNuSt {
    private final Ally inst;
    public AllyStruct(Ally _instance) {
        inst=(_instance);
    }
    public Ally getAlly() {
        return inst;
    }
}
