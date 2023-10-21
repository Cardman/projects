package code.gui;

import code.gui.events.AbsMouseListenerIntRel;

public final class SelectingComboListEvent implements AbsMouseListenerIntRel {
    private final ScrollCustomCombo component;

    public SelectingComboListEvent(ScrollCustomCombo _c) {
        this.component = _c;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        component.getSelected().requestFocusInWindow();
        if (!component.isEnabled()) {
            return;
        }
        component.togglePopup();
    }
}
