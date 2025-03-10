package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateMovesAfterFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateMovesAfterFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateMovesAfterFight();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
