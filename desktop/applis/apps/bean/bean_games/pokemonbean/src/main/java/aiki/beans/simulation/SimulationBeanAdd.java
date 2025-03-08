package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanAdd implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanAdd(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.add();
    }

}
