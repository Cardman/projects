package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanSelectPkEvosValidation implements IntBeanAction {
    private final SimulationBean bean;
    private final int index;

    public SimulationBeanSelectPkEvosValidation(SimulationBean _b, int _i) {
        this.index = _i;
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setSelectedPk(index);
        bean.displayEvolutions();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
