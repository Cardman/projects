package aiki.beans;

import aiki.beans.fight.*;

public final class BeanAnchorToFighterEvent implements IntBeanAction{
    private final int constant;
    private final StringMapObject forms;

    public BeanAnchorToFighterEvent(int _cst, StringMapObject _f) {
        constant = _cst;
        forms = _f;
    }

    @Override
    public String actionBean() {
        return TeamBean.clickFighter(constant, forms);
    }

}
