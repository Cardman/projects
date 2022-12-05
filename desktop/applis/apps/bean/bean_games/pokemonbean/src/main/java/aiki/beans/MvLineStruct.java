package aiki.beans;

import aiki.beans.facade.dto.MoveLine;
import code.bean.nat.NaNuSt;

public final class MvLineStruct extends NaNuSt {
    private final MoveLine inst;
    public MvLineStruct(MoveLine _wildPk) {
        inst=(_wildPk);
    }

    public MoveLine getWildPk() {
        return inst;
    }
}
