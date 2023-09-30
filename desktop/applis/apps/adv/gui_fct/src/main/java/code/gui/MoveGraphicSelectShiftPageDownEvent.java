package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectShiftPageDownEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectShiftPageDownEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.moveShiftPageDown();
    }
}
