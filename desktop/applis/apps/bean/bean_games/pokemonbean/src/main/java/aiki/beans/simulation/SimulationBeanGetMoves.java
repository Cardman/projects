package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanGetMoves implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getMoves(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
