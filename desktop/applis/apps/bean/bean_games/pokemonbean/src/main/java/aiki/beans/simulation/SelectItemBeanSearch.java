package aiki.beans.simulation;

import aiki.beans.*;

public final class SelectItemBeanSearch implements IntBeanAction {
    private final SelectItemBean bean;

    public SelectItemBeanSearch(SelectItemBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.search();
    }

}
