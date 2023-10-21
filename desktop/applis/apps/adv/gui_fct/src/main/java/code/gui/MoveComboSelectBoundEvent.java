package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboSelectBoundEvent implements AbsActionListener {
    private final int down;
    private final ScrollCustomCombo component;

    public MoveComboSelectBoundEvent(ScrollCustomCombo _c, int _d) {
        this.component = _c;
        down = _d;
    }
    @Override
    public void action() {
        component.goBound(down);
    }
}
