package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanStepFightCatch implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanStepFightCatch(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.stepFightCatch();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }


}
