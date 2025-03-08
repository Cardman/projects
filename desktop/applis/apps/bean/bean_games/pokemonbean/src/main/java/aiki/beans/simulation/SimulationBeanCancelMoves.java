package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanCancelMoves implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanCancelMoves(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.cancelMoves();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
