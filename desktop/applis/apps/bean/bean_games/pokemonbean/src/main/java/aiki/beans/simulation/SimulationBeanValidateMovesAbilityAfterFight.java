package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateMovesAbilityAfterFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateMovesAbilityAfterFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateMovesAbilityAfterFight();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
