package aiki.beans.abilities;

import aiki.beans.*;
public final class AbilityBeanClickIndex implements IntBeanAction {
    private final AbilityBean bean;

    public AbilityBeanClickIndex(AbilityBean _p) {
        bean = _p;
    }

    @Override
    public String actionBean() {
        return bean.clickIndex();
    }

}
