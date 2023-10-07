package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectShiftPageEvent<T> implements AbsActionListener {
    private final int down;
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectShiftPageEvent(ScrollCustomGraphicList<T> _c, int _d) {
        this.component = _c;
        down = _d;
    }
    @Override
    public void action() {
        component.moveShiftPage(down);
    }
}
