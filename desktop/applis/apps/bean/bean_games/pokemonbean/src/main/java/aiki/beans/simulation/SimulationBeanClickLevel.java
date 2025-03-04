package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class SimulationBeanClickLevel implements NatCaller, IntBeanAction {
    private final SimulationBean bean;
    private final int place;
    private final int level;
    public SimulationBeanClickLevel() {
        this(null, 0,0);
    }
    public SimulationBeanClickLevel(SimulationBean _b, int _p, int _l) {
        bean = _b;
        this.place = _p;
        this.level = _l;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[]{new NaNbSt(place),new NaNbSt(level)})).getInstance();
    }

    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).clickLevel(NaPa.convertToNumber(_args[0]).intStruct(),NaPa.convertToNumber(_args[1]).intStruct()));
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
