package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanAddPkTrainer implements IntBeanAction {

    private final SimulationBean bean;

    public SimulationBeanAddPkTrainer(SimulationBean _b) {
        bean = _b;
    }
    @Override
    public String actionBean() {
        return bean.addPkTrainer();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
