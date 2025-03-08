package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateEvo implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateEvo(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateEvo();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
