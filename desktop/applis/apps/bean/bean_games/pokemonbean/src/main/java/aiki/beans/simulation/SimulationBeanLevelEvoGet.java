package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanLevelEvoGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getLevelEvo().valueLong());
    }
}
