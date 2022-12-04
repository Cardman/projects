package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanNumberNecessaryPointsForGrowingLevel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).numberNecessaryPointsForGrowingLevel(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
