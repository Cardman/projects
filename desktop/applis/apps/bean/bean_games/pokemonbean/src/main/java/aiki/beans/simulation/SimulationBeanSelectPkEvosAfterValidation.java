package aiki.beans.simulation;

import aiki.beans.*;
import code.scripts.confs.*;

public final class SimulationBeanSelectPkEvosAfterValidation implements IntBeanAction {
    private final SimulationBean bean;
    private final int index;

    public SimulationBeanSelectPkEvosAfterValidation(SimulationBean _b, int _i) {
        this.index = _i;
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setSelectedPk(index);
        bean.selectPkAfterFight();
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
