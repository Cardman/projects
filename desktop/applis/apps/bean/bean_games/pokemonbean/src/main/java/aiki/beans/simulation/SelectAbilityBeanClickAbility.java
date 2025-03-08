package aiki.beans.simulation;

import aiki.beans.*;

public final class SelectAbilityBeanClickAbility implements IntBeanAction {
    private final SelectAbilityBean bean;
    private final int index;

    public SelectAbilityBeanClickAbility(SelectAbilityBean _b,int _i) {
        bean = _b;
        index = _i;
    }

    @Override
    public String actionBean() {
        return bean.clickAbility(index);
    }

}
