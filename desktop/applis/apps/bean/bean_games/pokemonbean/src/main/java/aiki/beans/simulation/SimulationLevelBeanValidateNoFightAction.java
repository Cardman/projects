package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationLevelBeanValidateNoFightAction implements IntBeanAction {
    private final SimulationLevelBean bean;

    public SimulationLevelBeanValidateNoFightAction(SimulationLevelBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.noFight(bean.getNoFight().valueLong());
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML;
    }

}
