package aiki.beans.simulation;

import aiki.beans.*;

import code.scripts.confs.PkScriptPages;

public final class SimulationBeanChangeFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanChangeFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.changeFight();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
