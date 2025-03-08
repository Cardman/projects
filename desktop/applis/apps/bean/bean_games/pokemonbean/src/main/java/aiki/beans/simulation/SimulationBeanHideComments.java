package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanHideComments implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanHideComments(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.hideComments();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
