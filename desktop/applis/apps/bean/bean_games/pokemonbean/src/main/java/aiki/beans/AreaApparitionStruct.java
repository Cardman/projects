package aiki.beans;

import aiki.map.levels.AreaApparition;
import code.bean.nat.NaNuSt;

public final class AreaApparitionStruct extends NaNuSt {
    private final AreaApparition inst;
    public AreaApparitionStruct(AreaApparition _wildPk) {
        inst=(_wildPk);
    }

    public AreaApparition getWildPk() {
        return inst;
    }
}
