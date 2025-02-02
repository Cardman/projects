package code.gui.document;

import aiki.beans.IntBeanAction;
import aiki.beans.IntBeanBuilderHelper;
import code.gui.*;
import code.gui.events.*;

public final class BeanAnchorEvent implements AbsMouseListenerIntRel, AbsActionListener {
    private final IntBeanBuilderHelper helper;
    private final IntBeanAction action;

    public BeanAnchorEvent(IntBeanBuilderHelper _h, IntBeanAction _n) {
        this.helper = _h;
        this.action = _n;
    }

    @Override
    public void action() {
        mouseReleased(null,null,null);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        build();
    }

    public void build() {
        helper.build(action);
    }
}
