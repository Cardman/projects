package aiki.beans.simulation;

import aiki.beans.*;
import code.scripts.confs.*;

public final class SimulationBeanValidateMovesSets implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateMovesSets(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateMovesSets();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    @Override
    public CommonBean getBean() {
        return bean;
    }
}
