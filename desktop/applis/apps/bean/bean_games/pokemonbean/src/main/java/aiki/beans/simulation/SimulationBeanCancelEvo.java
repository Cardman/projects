package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanCancelEvo implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanCancelEvo(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.cancelEvo();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
