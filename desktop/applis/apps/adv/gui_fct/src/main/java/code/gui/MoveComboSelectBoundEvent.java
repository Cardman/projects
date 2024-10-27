package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboSelectBoundEvent<T> implements AbsActionListener {
    private final int down;
    private final AbsScrollCustomCombo<T> component;

    public MoveComboSelectBoundEvent(AbsScrollCustomCombo<T> _c, int _d) {
        this.component = _c;
        down = _d;
    }
    @Override
    public void action() {
        component.goBound(down);
    }
}
