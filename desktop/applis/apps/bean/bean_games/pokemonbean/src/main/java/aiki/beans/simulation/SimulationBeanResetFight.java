package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanResetFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanResetFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.resetFight();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
