package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicFocusEvent<T> implements AbsActionListener {
    private final int down;
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicFocusEvent(ScrollCustomGraphicList<T> _c, int _d) {
        down = _d;
        this.component = _c;
    }
    @Override
    public void action() {
        component.moveFocus(down);
    }
}
