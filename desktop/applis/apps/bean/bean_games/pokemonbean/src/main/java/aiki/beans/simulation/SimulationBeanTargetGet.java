package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanTargetGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(Long.toString(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getTarget().valueInt()));
    }
}
