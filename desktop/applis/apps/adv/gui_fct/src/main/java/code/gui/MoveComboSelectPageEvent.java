package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboSelectPageEvent implements AbsActionListener {
    private final int down;
    private final ScrollCustomCombo component;

    public MoveComboSelectPageEvent(ScrollCustomCombo _c, int _d) {
        this.component = _c;
        this.down = _d;
    }
    @Override
    public void action() {
        component.movePage(down);
    }
}
