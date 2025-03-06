package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanRoundGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getIntStr(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getRound());
    }
}
