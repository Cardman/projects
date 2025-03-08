package aiki.beans.map;

import aiki.beans.*;

public final class MapLevelBeanClickAreaOnMap implements IntBeanAction {
    private final AbsLevelBean bean;
    private final int index;

    public MapLevelBeanClickAreaOnMap(AbsLevelBean _m, int _i) {
        bean = _m;
        index = _i;
    }

    @Override
    public String actionBean() {
        return bean.clickAreaOnMap(index);
    }
}
