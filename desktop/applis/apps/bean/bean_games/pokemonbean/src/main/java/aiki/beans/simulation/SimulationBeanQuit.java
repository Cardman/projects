package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanQuit implements IntBeanAction {
    private final SimulationBean bean;
    public SimulationBeanQuit(SimulationBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.quit();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
