package aiki.beans.simulation;

import aiki.beans.*;
public final class SimulationLevelBeanClickTile implements IntBeanAction {

    private final SimulationLevelBean bean;
    private final int index;

    public SimulationLevelBeanClickTile(SimulationLevelBean _b,int _i) {
        bean = _b;
        index = _i;
    }

    @Override
    public String actionBean() {
        return bean.clickTile(index);
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
