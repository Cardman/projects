package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboToggleEvent<T> implements AbsActionListener {
    private final AbsScrollCustomCombo<T> component;

    public MoveComboToggleEvent(AbsScrollCustomCombo<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.togglePopup();
    }
}
