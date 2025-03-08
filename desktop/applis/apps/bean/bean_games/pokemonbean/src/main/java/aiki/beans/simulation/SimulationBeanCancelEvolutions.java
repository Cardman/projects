package aiki.beans.simulation;

import aiki.beans.*;

import code.scripts.confs.*;

public final class SimulationBeanCancelEvolutions implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanCancelEvolutions(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.cancelEvolutions();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
