package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanSelectedFoeActionGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getSelectedFoeAction());
    }
}
