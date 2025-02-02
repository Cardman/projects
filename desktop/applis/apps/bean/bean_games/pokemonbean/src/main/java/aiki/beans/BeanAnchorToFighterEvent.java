package aiki.beans;

import aiki.beans.fight.*;

public final class BeanAnchorToFighterEvent implements IntBeanAction{
    private final int constant;
    private final TeamBean bean;

    public BeanAnchorToFighterEvent(int _cst, TeamBean _b) {
        bean = _b;
        constant = _cst;
    }

    @Override
    public String actionBean() {
        return bean.clickFighter(constant);
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
