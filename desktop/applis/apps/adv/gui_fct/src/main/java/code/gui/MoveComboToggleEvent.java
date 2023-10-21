package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboToggleEvent implements AbsActionListener {
    private final ScrollCustomCombo component;

    public MoveComboToggleEvent(ScrollCustomCombo _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.togglePopup();
    }
}
