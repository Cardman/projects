package aiki.beans.map;

import aiki.beans.*;

public final class MapLevelBeanClickNeighbour implements IntBeanAction {
    private final AbsLevelBean bean;
    private final int index;

    public MapLevelBeanClickNeighbour(AbsLevelBean _m, int _i) {
        bean = _m;
        index = _i;
    }

    @Override
    public String actionBean() {
        return bean.clickNeighbour(index);
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
