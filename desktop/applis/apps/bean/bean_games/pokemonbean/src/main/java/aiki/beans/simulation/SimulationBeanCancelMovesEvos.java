package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanCancelMovesEvos implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanCancelMovesEvos(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.cancelMovesEvos();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
