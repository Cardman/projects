package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;

public class SimulationBeanValidateMovesChoice implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).validateMovesChoice();
        return new NaStSt(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML);
    }
}
