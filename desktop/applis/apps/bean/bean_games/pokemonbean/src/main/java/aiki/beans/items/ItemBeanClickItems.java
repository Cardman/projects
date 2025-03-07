package aiki.beans.items;

import aiki.beans.*;
public final class ItemBeanClickItems implements IntBeanAction {
    private final ItemBean bean;

    public ItemBeanClickItems(ItemBean _p) {
        bean = _p;
    }

    @Override
    public String actionBean() {
        return bean.clickItems();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
