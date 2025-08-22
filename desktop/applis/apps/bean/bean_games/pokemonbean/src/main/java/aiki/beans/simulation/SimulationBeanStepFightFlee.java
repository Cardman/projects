package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanStepFightFlee implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanStepFightFlee(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.stepFightFlee();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }


}
