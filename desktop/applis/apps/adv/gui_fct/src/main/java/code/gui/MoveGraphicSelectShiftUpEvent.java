package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectShiftUpEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectShiftUpEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.moveShiftUp();
    }
}
