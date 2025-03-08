package aiki.beans.moves;

import aiki.beans.*;
public final class MoveBeanClickMoves implements IntBeanAction {
    private final MoveBean bean;

    public MoveBeanClickMoves(MoveBean _m) {
        bean = _m;
    }

    @Override
    public String actionBean() {
        return bean.clickMoves();
    }

}
