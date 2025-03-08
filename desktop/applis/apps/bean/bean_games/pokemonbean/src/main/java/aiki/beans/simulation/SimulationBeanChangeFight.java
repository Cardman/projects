package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanChangeFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanChangeFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.changeFight();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
