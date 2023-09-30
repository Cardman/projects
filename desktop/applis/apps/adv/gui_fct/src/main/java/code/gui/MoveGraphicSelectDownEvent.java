package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectDownEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectDownEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.moveDown();
    }
}
