package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanSelectFoePkValidation implements IntBeanAction {
    private final SimulationBean bean;
    private final String act;
    private final int index;

    public SimulationBeanSelectFoePkValidation(SimulationBean _b, String _a, int _i) {
        this.index = _i;
        this.act = _a;
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setSelectedFoeAction(act);
        bean.setSelectedFoePk(index);
        return bean.selectFoePk();
    }

}
