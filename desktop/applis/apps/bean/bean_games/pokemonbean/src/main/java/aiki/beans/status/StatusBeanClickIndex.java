package aiki.beans.status;

import aiki.beans.*;
public final class StatusBeanClickIndex implements IntBeanAction {
    private final StatusBean bean;

    public StatusBeanClickIndex(StatusBean _p) {
        bean = _p;
    }

    @Override
    public String actionBean() {
        return bean.clickIndex();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
