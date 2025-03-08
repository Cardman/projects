package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateEvolutions implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateEvolutions(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateEvolutions();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
