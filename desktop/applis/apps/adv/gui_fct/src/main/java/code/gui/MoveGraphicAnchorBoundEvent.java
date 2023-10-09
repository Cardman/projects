package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicAnchorBoundEvent<T> implements AbsActionListener {
    private final int down;
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicAnchorBoundEvent(ScrollCustomGraphicList<T> _c, int _d) {
        this.component = _c;
        down = _d;
    }
    @Override
    public void action() {
        component.anchorBound(down);
    }
}
