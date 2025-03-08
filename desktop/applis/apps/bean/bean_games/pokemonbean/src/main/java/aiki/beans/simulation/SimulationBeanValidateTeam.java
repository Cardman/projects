package aiki.beans.simulation;

import aiki.beans.*;
import code.scripts.confs.*;

public final class SimulationBeanValidateTeam implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateTeam(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateTeam();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }


}
