package aiki.beans.moves;

import aiki.beans.*;
public final class MovesBeanSearch implements IntBeanAction {
    private final MovesBean bean;
    public MovesBeanSearch(MovesBean _m) {
        bean = _m;
    }

    @Override
    public String actionBean() {
        return bean.search();
    }

}
