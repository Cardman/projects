package aiki.beans.simulation;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class SimulationBeanRemoveCst implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return new StringStruct("REMOVE");
    }
}
