package aiki.beans;

import aiki.beans.facade.simulation.dto.EvLine;

public final class EvLineStruct extends ParamNatStruct<EvLine> {
    public EvLineStruct(EvLine _evLine,String _className) {
        super(_evLine,_className);
    }

    public EvLine getEvLine() {
        return getInstance();
    }
}
