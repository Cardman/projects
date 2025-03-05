package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationLevelBeanNoFightGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (SimulationLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getNoFight().valueLong());
    }
}
