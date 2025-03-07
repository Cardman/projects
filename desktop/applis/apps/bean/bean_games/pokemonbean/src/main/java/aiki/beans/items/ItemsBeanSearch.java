package aiki.beans.items;

import aiki.beans.*;
public final class ItemsBeanSearch implements IntBeanAction {
    private final ItemsBean bean;
    public ItemsBeanSearch(ItemsBean _m) {
        bean = _m;
    }

    @Override
    public String actionBean() {
        return bean.search();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
