package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectShiftEndEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectShiftEndEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.moveShiftBottom();
    }
}
