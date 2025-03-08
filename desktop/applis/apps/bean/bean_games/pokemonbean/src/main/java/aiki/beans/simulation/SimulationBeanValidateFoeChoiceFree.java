package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateFoeChoiceFree implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateFoeChoiceFree(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateFoeChoiceFree();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
