package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanClickLevelZero implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).clickLevel(NaPa.convertToNumber(_args[0]).intStruct(),0));
    }
}
