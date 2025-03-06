package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanMultiplicitySet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getMultiplicity().valueLong(NaPa.convertToNumber(_args[0]).intStruct());
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).setMultiplicity(NaPa.convertToNumber(_args[0]).intStruct());
        return NaNu.NULL_VALUE;
    }
}
