package aiki.beans.simulation;

import aiki.beans.*;
public final class SimulationBeanClickLevel implements IntBeanAction {
    private final SimulationBean bean;
    private final int place;
    private final int level;

    public SimulationBeanClickLevel(SimulationBean _b, int _p, int _l) {
        bean = _b;
        this.place = _p;
        this.level = _l;
    }

    @Override
    public String actionBean() {
        return bean.clickLevel(place,level);
    }

}
