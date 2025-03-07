package aiki.beans.simulation;

import aiki.beans.*;

import code.scripts.confs.*;

public final class SimulationBeanValidateEvo implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateEvo(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateEvo();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    @Override
    public CommonBean getBean() {
        return bean;
    }
}
