package aiki.beans;

import aiki.beans.facade.simulation.dto.EvLine;

public final class EvLineStruct extends ParamNatStruct<EvLine> {
    public EvLineStruct(EvLine _evLine) {
        super(_evLine);
    }

    public EvLine getEvLine() {
        return getInstance();
    }
}
