package code.gui;

import code.gui.events.AbsMouseListenerIntRel;

public final class SelectingComboListEvent<T> implements AbsMouseListenerIntRel {
    private final AbsScrollCustomCombo<T> component;

    public SelectingComboListEvent(AbsScrollCustomCombo<T> _c) {
        this.component = _c;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!component.isEnabled()) {
            return;
        }
        component.getSelected().requestFocusInWindow();
        component.togglePopup();
    }
}
