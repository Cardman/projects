package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboSelectPageEvent<T> implements AbsActionListener {
    private final int down;
    private final AbsScrollCustomCombo<T> component;

    public MoveComboSelectPageEvent(AbsScrollCustomCombo<T> _c, int _d) {
        this.component = _c;
        this.down = _d;
    }
    @Override
    public void action() {
        component.movePage(down);
    }
}
