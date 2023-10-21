package code.gui;

import code.gui.events.AbsMouseListenerIntRel;

public final class MoveComboHideAfterMouseEvent implements AbsMouseListenerIntRel {
    private final ScrollCustomCombo component;

    public MoveComboHideAfterMouseEvent(ScrollCustomCombo _c) {
        this.component = _c;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        int y_ = _location.getYcoord();
        component.getList().selectCoords(y_);
        component.changeSelect();
    }
}
