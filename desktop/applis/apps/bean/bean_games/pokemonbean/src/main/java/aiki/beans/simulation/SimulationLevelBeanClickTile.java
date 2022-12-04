package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationLevelBeanClickTile implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SimulationLevelBean) ((PokemonBeanStruct)_instance).getInstance()).clickTile(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
