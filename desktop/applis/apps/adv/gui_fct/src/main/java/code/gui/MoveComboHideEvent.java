package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboHideEvent<T> implements AbsActionListener {
    private final AbsScrollCustomCombo<T> component;

    public MoveComboHideEvent(AbsScrollCustomCombo<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.hidePopup();
    }
}
