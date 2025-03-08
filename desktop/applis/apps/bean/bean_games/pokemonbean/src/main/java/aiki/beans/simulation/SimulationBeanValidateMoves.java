package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateMoves implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateMoves(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateMoves();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
