package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateEvolutionAfterFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateEvolutionAfterFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateEvolutionAfterFight();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
