package aiki.beans.simulation;

import aiki.beans.*;
public final class SelectItemBeanCancel implements IntBeanAction {
    private final SelectItemBean bean;

    public SelectItemBeanCancel(SelectItemBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.cancel();
    }

}
