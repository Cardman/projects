package aiki.beans.simulation;

import aiki.beans.*;
import code.scripts.confs.*;

public final class SimulationLevelBeanValidateNoFightAction implements IntBeanAction {
    private final SimulationLevelBean bean;

    public SimulationLevelBeanValidateNoFightAction(SimulationLevelBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.noFight(bean.getNoFight().valueLong());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML;
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
