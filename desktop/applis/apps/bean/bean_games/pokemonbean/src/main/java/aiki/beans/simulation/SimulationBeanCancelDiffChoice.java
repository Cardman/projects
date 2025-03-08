package aiki.beans.simulation;

import aiki.beans.*;

import code.scripts.confs.*;

public final class SimulationBeanCancelDiffChoice implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanCancelDiffChoice(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.cancelDiffChoice();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
