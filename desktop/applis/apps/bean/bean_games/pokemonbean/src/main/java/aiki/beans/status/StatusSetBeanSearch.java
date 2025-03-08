package aiki.beans.status;

import aiki.beans.*;
public final class StatusSetBeanSearch implements IntBeanAction {
    private final StatusSetBean bean;

    public StatusSetBeanSearch(StatusSetBean _m) {
        bean = _m;
    }

    @Override
    public String actionBean() {
        return bean.search();
    }

}
