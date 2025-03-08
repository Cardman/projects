package aiki.beans.simulation;

import aiki.beans.*;

public final class SelectAbilityBeanSearch implements IntBeanAction {
    private final SelectAbilityBean bean;

    public SelectAbilityBeanSearch(SelectAbilityBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.search();
    }

}
