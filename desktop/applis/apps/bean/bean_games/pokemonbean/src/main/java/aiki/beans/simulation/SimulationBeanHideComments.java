package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanHideComments implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).hideComments();
        return new NaStSt(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML);
    }
}
