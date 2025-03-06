package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.IntBeanAction;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;

public final class SimulationBeanCancelMoves implements NatCaller, IntBeanAction {
    private final SimulationBean bean;
    public SimulationBeanCancelMoves() {
        this(null);
    }
    public SimulationBeanCancelMoves(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).cancelMoves();
        return new NaStSt(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML);
    }
    @Override
    public CommonBean getBean() {
        return bean;
    }
}
