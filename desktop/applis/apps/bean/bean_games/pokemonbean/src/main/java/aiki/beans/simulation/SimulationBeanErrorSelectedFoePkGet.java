package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;

public class SimulationBeanErrorSelectedFoePkGet implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return BooleanStruct.of(((SimulationBean)((PokemonBeanStruct)_instance).getBean()).errorSelectedFoePk());
    }
}
