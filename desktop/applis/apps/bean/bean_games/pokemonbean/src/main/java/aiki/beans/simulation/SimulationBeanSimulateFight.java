package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanSimulateFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanSimulateFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.simulateFight();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
