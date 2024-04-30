package code.gui;

import code.gui.events.AbsMouseListenerIntRel;

public final class SelectingGraphicListEvent<T> implements AbsMouseListenerIntRel {
    private final ScrollCustomGraphicList<T> component;

    public SelectingGraphicListEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!component.isEnabled()) {
            return;
        }
        component.getElements().requestFocusInWindow();
        int y_ = _location.getYcoord();
        if (_keyState.isControlDown()) {
            if (_keyState.isShiftDown()) {
                component.extendFromAnchor(y_);
            } else {
                component.addOrRemoveToSelectCoords(y_);
            }
        } else {
            if (_keyState.isShiftDown()) {
                component.extendsCoords(y_);
            } else {
                component.selectCoords(y_);
            }
        }
    }
}
