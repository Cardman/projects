package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;

public class SimulationBeanMultiplicityGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).multiplicity());
    }
}
