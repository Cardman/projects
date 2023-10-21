package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboHideEvent implements AbsActionListener {
    private final ScrollCustomCombo component;

    public MoveComboHideEvent(ScrollCustomCombo _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.hidePopup();
    }
}
