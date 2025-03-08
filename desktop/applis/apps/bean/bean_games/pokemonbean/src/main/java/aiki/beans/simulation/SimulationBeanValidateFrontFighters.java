package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateFrontFighters implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateFrontFighters(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateFrontFighters();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
