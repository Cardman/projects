package aiki.beans.simulation;

import aiki.beans.*;
import code.scripts.confs.*;

public class SimulationBeanValidateMovesAfterFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateMovesAfterFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateMovesAfterFight();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
