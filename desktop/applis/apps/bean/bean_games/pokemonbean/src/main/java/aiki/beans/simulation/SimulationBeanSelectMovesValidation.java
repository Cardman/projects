package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;

public final class SimulationBeanSelectMovesValidation implements IntBeanAction {
    private final SimulationBean bean;
    private final int index;

    public SimulationBeanSelectMovesValidation(SimulationBean _b, int _i) {
        this.index = _i;
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setSelectedMove(index);
        return ((NaStSt)new SimulationBeanValidateMovesChoice().re(new PokemonBeanStruct(bean),new NaSt[0])).getInstance();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
