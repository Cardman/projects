package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.LgIntStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanGetRemainingLifeRate implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new LgIntStruct(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getRemainingLifeRate(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
