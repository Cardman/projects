package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanSelectPkValidation implements IntBeanAction {
    private final SimulationBean bean;
    private final String act;
    private final int index;

    public SimulationBeanSelectPkValidation(SimulationBean _b, String _a, int _i) {
        this.index = _i;
        this.act = _a;
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setSelectedAction(act);
        bean.setSelectedPk(index);
        return bean.selectPk();
    }

}
