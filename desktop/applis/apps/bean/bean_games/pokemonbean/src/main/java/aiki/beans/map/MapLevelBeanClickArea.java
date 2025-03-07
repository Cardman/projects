package aiki.beans.map;

import aiki.beans.*;

public final class MapLevelBeanClickArea implements IntBeanAction {
    private final AbsLevelBean bean;
    private final int index;

    public MapLevelBeanClickArea(AbsLevelBean _m, int _i) {
        bean = _m;
        index = _i;
    }

    @Override
    public String actionBean() {
        return bean.clickArea(index);
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
