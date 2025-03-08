package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanChangeFightWhileEnd implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanChangeFightWhileEnd(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.changeFightWhileEnd();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
