package aiki.beans.map;

import aiki.beans.*;
public final class MapLevelBeanClickTileOnMap implements IntBeanAction {
    private final MapLevelBean bean;
    private final int index;

    public MapLevelBeanClickTileOnMap(MapLevelBean _m, int _i) {
        bean = _m;
        index = _i;
    }

    @Override
    public String actionBean() {
        return bean.clickTileOnMap(index);
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
