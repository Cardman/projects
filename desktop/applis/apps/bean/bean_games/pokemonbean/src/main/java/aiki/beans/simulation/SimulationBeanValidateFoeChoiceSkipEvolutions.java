package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanValidateFoeChoiceSkipEvolutions implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateFoeChoiceSkipEvolutions(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateFoeChoiceSkipEvolutions();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
