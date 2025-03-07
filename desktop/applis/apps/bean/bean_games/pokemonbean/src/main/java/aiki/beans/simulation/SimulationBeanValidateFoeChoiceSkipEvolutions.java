package aiki.beans.simulation;

import aiki.beans.*;
import code.scripts.confs.*;

public final class SimulationBeanValidateFoeChoiceSkipEvolutions implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateFoeChoiceSkipEvolutions(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateFoeChoiceSkipEvolutions();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    @Override
    public CommonBean getBean() {
        return bean;
    }
}
