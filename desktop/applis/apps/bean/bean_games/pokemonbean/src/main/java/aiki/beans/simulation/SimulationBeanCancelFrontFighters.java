package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanCancelFrontFighters implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanCancelFrontFighters(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.cancelFrontFighters();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
