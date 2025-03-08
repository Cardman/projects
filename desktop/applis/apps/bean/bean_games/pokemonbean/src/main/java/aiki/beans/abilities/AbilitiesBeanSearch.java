package aiki.beans.abilities;

import aiki.beans.*;
public final class AbilitiesBeanSearch implements IntBeanAction {
    private final AbilitiesBean bean;

    public AbilitiesBeanSearch(AbilitiesBean _m) {
        bean = _m;
    }

    @Override
    public String actionBean() {
        return bean.search();
    }

}
