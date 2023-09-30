package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectEndEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectEndEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.goBottom();
    }
}
