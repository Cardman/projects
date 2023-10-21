package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboEnterEvent implements AbsActionListener {
    private final ScrollCustomCombo component;

    public MoveComboEnterEvent(ScrollCustomCombo _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.changeSelect();
    }
}
