package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectEvent<T> implements AbsActionListener {
    private final int down;
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectEvent(ScrollCustomGraphicList<T> _c, int _d) {
        down = _d;
        this.component = _c;
    }
    @Override
    public void action() {
        component.move(down);
    }
}
