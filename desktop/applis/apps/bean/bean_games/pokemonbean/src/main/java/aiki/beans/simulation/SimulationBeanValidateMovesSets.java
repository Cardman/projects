package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateMovesSets implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateMovesSets(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateMovesSets();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
