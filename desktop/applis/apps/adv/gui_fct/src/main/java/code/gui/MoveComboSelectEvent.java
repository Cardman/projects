package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboSelectEvent implements AbsActionListener {
    private final int down;
    private final ScrollCustomCombo component;

    public MoveComboSelectEvent(ScrollCustomCombo _c, int _d) {
        down = _d;
        this.component = _c;
    }
    @Override
    public void action() {
        component.move(down);
    }
}
