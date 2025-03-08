package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanDisplayComments implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanDisplayComments(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.displayComments();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
