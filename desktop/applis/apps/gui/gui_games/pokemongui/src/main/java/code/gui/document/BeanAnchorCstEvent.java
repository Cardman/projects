package code.gui.document;

import aiki.beans.*;
import code.util.*;

public final class BeanAnchorCstEvent extends BeanAnchorEvent{
    private final String constant;
    private final StringMapObject formBase;
    public BeanAnchorCstEvent(String _cst, StringMap<AbsBeanRender> _r, StringMapObject _form) {
        super(_r);
        constant = _cst;
        formBase = _form;
    }

    @Override
    protected void action(StringMap<AbsBeanRender> _r) {
        AbsBeanRender target_ = _r.getVal(constant);
        target_.getScrollPane().setViewportView(target_.build(target_.getFactory(),target_.getFacade(),target_.getEvent(), formBase));
        target_.getScrollPane().validate();
        target_.getFrame().pack();
    }
}
