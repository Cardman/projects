package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;

public class SimulationBeanChangeFightWhileEnd implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).changeFightWhileEnd();
        return new NaStSt(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML);
    }
}
