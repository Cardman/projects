package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicFocusPageEvent<T> implements AbsActionListener {
    private final int down;
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicFocusPageEvent(ScrollCustomGraphicList<T> _c, int _d) {
        this.component = _c;
        this.down = _d;
    }
    @Override
    public void action() {
        component.movePageFocus(down);
    }
}
