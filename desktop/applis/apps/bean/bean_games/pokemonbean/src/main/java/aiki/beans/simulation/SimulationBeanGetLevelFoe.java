package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanGetLevelFoe implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getLevelFoe(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
