package code.gui.document;

import aiki.beans.fight.*;
import code.util.*;

public final class BeanAnchorToFighterEvent extends BeanAnchorEvent{
    private final int constant;
    private final TeamBean bean;
    public BeanAnchorToFighterEvent(int _cst, TeamBean _b, StringMap<AbsBeanRender> _r) {
        super(_r);
        bean = _b;
        constant = _cst;
    }

    @Override
    protected void action(StringMap<AbsBeanRender> _r) {
        AbsBeanRender target_ = _r.getVal(bean.clickFighter(constant));
        target_.getScrollPane().setViewportView(target_.build(target_.getFactory(),target_.getFacade(),target_.getEvent(), bean.getForms()));
        target_.getScrollPane().validate();
        target_.getFrame().pack();
    }
}
