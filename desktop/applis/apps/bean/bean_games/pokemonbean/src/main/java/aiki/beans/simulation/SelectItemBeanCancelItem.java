package aiki.beans.simulation;

import aiki.beans.*;

public final class SelectItemBeanCancelItem implements IntBeanAction {
    private final SelectItemBean bean;

    public SelectItemBeanCancelItem(SelectItemBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.cancelItem();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
