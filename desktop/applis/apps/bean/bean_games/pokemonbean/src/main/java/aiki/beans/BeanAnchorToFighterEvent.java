package aiki.beans;

import aiki.beans.fight.*;

public final class BeanAnchorToFighterEvent implements IntBeanAction{
    private final int constant;
    private final CommonBean bean;
    private final StringMapObject forms;

    public BeanAnchorToFighterEvent(int _cst, CommonBean _b, StringMapObject _f) {
        bean = _b;
        constant = _cst;
        forms = _f;
    }

    @Override
    public String actionBean() {
        return TeamBean.clickFighter(constant, forms);
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
