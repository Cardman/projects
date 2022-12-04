package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanGetLevelAlly implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getLevelAlly(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
