package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectShiftDownEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectShiftDownEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.moveShiftDown();
    }
}
