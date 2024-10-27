package code.gui;

import code.gui.events.AbsMouseListenerIntRel;

public final class MoveComboHideAfterMouseEvent<T> implements AbsMouseListenerIntRel {
    private final AbsScrollCustomCombo<T> component;

    public MoveComboHideAfterMouseEvent(AbsScrollCustomCombo<T> _c) {
        this.component = _c;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        int y_ = _location.getYcoord();
        component.getList().selectCoords(y_);
        component.changeSelect();
    }
}
