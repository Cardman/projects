package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;

public class SimulationBeanEnvironmentGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).environment());
    }
}
