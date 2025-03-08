package aiki.beans.simulation;

import aiki.beans.*;
import code.scripts.confs.*;

public final class SimulationBeanSelectPkMovesValidation implements IntBeanAction {
    private final SimulationBean bean;
    private final int index;

    public SimulationBeanSelectPkMovesValidation(SimulationBean _b, int _i) {
        this.index = _i;
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setSelectedPk(index);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
