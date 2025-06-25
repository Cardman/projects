package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanStepFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanStepFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.stepFight();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }


}
