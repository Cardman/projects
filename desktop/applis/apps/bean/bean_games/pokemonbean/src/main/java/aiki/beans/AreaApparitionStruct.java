package aiki.beans;

import aiki.map.levels.AbsAreaApparition;
import code.bean.nat.NaNuSt;

public final class AreaApparitionStruct extends NaNuSt {
    private final AbsAreaApparition inst;
    public AreaApparitionStruct(AbsAreaApparition _wildPk) {
        inst=(_wildPk);
    }

    public AbsAreaApparition getWildPk() {
        return inst;
    }
}
