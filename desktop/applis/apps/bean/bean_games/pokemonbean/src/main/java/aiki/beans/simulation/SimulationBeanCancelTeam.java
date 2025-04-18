package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanCancelTeam implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanCancelTeam(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.cancelTeam();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }


}
