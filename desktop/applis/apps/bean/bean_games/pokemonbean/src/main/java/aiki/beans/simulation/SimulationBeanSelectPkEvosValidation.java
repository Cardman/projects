package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;

public final class SimulationBeanSelectPkEvosValidation implements IntBeanAction {
    private final SimulationBean bean;
    private final int index;

    public SimulationBeanSelectPkEvosValidation(SimulationBean _b, int _i) {
        this.index = _i;
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setSelectedPk(index);
        return ((NaStSt)new SimulationBeanDisplayEvolutions().re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
