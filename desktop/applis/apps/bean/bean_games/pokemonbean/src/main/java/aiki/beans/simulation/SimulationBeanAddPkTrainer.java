package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class SimulationBeanAddPkTrainer implements NatCaller, IntBeanAction {

    private final SimulationBean bean;
    public SimulationBeanAddPkTrainer() {
        this(null);
    }
    public SimulationBeanAddPkTrainer(SimulationBean _b) {
        bean = _b;
    }
    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).addPkTrainer());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
