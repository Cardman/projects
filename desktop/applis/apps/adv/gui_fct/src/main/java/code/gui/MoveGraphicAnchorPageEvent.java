package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicAnchorPageEvent<T> implements AbsActionListener {
    private final int down;
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicAnchorPageEvent(ScrollCustomGraphicList<T> _c, int _d) {
        this.component = _c;
        this.down = _d;
    }
    @Override
    public void action() {
        component.movePageAnchor(down);
    }
}
