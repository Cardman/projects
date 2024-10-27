package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboSelectEvent<T> implements AbsActionListener {
    private final int down;
    private final AbsScrollCustomCombo<T> component;

    public MoveComboSelectEvent(AbsScrollCustomCombo<T> _c, int _d) {
        down = _d;
        this.component = _c;
    }
    @Override
    public void action() {
        component.move(down);
    }
}
