package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.IntBeanAction;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NaSt;
import code.bean.nat.NaStSt;

public final class SimulationBeanSelectPkEvosAfterValidation implements IntBeanAction {
    private final SimulationBean bean;
    private final int index;

    public SimulationBeanSelectPkEvosAfterValidation(SimulationBean _b, int _i) {
        this.index = _i;
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setSelectedPk(index);
        return ((NaStSt)new SimulationBeanSelectPkAfterFight().re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
