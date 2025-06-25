package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateFightCoreForm implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateFightCoreForm(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateFightCoreForm();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
