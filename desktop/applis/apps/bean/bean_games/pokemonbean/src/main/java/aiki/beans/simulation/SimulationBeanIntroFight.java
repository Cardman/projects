package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanIntroFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanIntroFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.introFight();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }


}
