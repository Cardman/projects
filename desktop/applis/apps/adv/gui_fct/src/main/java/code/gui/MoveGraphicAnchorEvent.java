package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicAnchorEvent<T> implements AbsActionListener {
    private final int down;
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicAnchorEvent(ScrollCustomGraphicList<T> _c, int _d) {
        down = _d;
        this.component = _c;
    }
    @Override
    public void action() {
        component.moveAnchor(down);
    }
}
