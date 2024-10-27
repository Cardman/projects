package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveComboEnterEvent<T> implements AbsActionListener {
    private final AbsScrollCustomCombo<T> component;

    public MoveComboEnterEvent(AbsScrollCustomCombo<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.changeSelect();
    }
}
