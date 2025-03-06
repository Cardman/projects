package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.IntBeanAction;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;

public final class SimulationBeanValidateFoeChoiceFree implements NatCaller, IntBeanAction {
    private final SimulationBean bean;
    public SimulationBeanValidateFoeChoiceFree() {
        this(null);
    }
    public SimulationBeanValidateFoeChoiceFree(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }

    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).validateFoeChoiceFree();
        return new NaStSt(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML);
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
