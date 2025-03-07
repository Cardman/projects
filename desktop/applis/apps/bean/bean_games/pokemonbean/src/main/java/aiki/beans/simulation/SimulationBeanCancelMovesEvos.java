package aiki.beans.simulation;

import aiki.beans.*;

import code.scripts.confs.*;

public final class SimulationBeanCancelMovesEvos implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanCancelMovesEvos(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.cancelMovesEvos();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    @Override
    public CommonBean getBean() {
        return bean;
    }
}
