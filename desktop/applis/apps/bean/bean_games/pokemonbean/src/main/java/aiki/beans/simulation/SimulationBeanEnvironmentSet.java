package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanEnvironmentSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getEnvironment().setupValue(NaPa.getString(_args[0]).getInstance());
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).setEnvironment(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
