package code.gui.document;

import aiki.beans.fight.*;
import code.util.*;

public final class BeanAnchorToTeamEvent extends BeanAnchorEvent{
    private final int constant;
    private final FightBean bean;
    public BeanAnchorToTeamEvent(int _cst, FightBean _b, StringMap<AbsBeanRender> _r) {
        super(_r);
        bean = _b;
        constant = _cst;
    }

    @Override
    protected void action(StringMap<AbsBeanRender> _r) {
        AbsBeanRender target_ = _r.getVal(bean.click(constant));
        target_.getScrollPane().setViewportView(target_.build(target_.getFactory(),target_.getFacade(),target_.getEvent(), bean.getForms()));
        target_.getScrollPane().validate();
        target_.getFrame().pack();
    }
}
