package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class SimulationBeanErrorSelectedFoePkGet implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return NaBoSt.of(((SimulationBean)((PokemonBeanStruct)_instance).getBean()).errorSelectedFoePk());
    }
}
