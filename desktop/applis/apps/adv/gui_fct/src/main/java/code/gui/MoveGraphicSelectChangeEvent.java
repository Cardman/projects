package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectChangeEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectChangeEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.changeSelection();
    }
}
