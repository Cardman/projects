package aiki.beans.simulation;

import aiki.beans.*;

import code.scripts.confs.*;

public final class SimulationBeanSimulateFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanSimulateFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.simulateFight();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
