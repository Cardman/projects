package aiki.beans.simulation;

import aiki.beans.*;

import code.scripts.confs.*;

public final class SimulationBeanHideComments implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanHideComments(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.hideComments();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    @Override
    public CommonBean getBean() {
        return bean;
    }
}
