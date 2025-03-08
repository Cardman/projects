package aiki.beans.simulation;

import aiki.beans.*;

public final class SelectItemBeanClickLink implements IntBeanAction {
    private final SelectItemBean bean;
    private final TranslatedKey index;
    public SelectItemBeanClickLink(SelectItemBean _b, TranslatedKey _i) {
        bean = _b;
        index = _i;
    }
    @Override
    public String actionBean() {
        return bean.putName(index);
    }

}
