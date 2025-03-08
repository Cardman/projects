package aiki.beans.simulation;

import aiki.beans.*;

import code.scripts.confs.PkScriptPages;

public final class SimulationBeanCancelMoves implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanCancelMoves(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.cancelMoves();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
