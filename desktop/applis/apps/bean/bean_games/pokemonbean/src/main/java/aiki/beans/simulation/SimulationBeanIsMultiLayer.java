package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanIsMultiLayer implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).isMultiLayer(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
