package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateFoeChoice implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateFoeChoice(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateFoeChoice();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
