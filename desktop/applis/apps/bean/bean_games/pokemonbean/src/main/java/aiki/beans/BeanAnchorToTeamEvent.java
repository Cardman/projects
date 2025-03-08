package aiki.beans;

import aiki.beans.fight.*;

public final class BeanAnchorToTeamEvent implements IntBeanAction{
    private final int constant;
    private final FightBean bean;

    public BeanAnchorToTeamEvent(int _cst, FightBean _b) {
        bean = _b;
        constant = _cst;
    }

    @Override
    public String actionBean() {
        return bean.click(constant);
    }

}
