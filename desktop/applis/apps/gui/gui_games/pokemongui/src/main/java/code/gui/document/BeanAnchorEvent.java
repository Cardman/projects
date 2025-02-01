package code.gui.document;

import code.gui.*;
import code.gui.events.*;

public final class BeanAnchorEvent implements AbsMouseListenerIntRel, AbsActionListener {
    private final BeanBuilderHelper helper;
    private final IntBeanAction action;

    public BeanAnchorEvent(BeanBuilderHelper _h, IntBeanAction _n) {
        this.helper = _h;
        this.action = _n;
    }

    @Override
    public void action() {
        helper.build(action.actionBean(),action.getBean().getForms());
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        helper.build(action.actionBean(),action.getBean().getForms());
    }

}
