package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class SimulationLevelBeanClickTile implements NatCaller, IntBeanAction {

    private final SimulationLevelBean bean;
    private final int index;

    public SimulationLevelBeanClickTile() {
        this(null,0);
    }

    public SimulationLevelBeanClickTile(SimulationLevelBean _b,int _i) {
        bean = _b;
        index = _i;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[]{new NaNbSt(index)})).getInstance();
    }

    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SimulationLevelBean) ((PokemonBeanStruct)_instance).getInstance()).clickTile(NaPa.convertToNumber(_args[0]).intStruct()));
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
