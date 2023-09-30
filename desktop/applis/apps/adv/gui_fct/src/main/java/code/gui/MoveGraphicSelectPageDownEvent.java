package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectPageDownEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectPageDownEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.movePageDown();
    }
}
