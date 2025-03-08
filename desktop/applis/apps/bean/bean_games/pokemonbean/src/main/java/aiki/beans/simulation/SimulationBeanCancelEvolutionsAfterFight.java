package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanCancelEvolutionsAfterFight implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanCancelEvolutionsAfterFight(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.cancelEvolutionsAfterFight();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
