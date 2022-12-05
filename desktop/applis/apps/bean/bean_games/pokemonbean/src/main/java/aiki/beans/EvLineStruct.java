package aiki.beans;

import aiki.beans.facade.simulation.dto.EvLine;
import code.bean.nat.NaNuSt;

public final class EvLineStruct extends NaNuSt {
    private final EvLine inst;
    public EvLineStruct(EvLine _evLine) {
        inst=(_evLine);
    }

    public EvLine getEvLine() {
        return inst;
    }
}
