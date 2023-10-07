package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicFocusBoundEvent<T> implements AbsActionListener {
    private final int down;
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicFocusBoundEvent(ScrollCustomGraphicList<T> _c, int _d) {
        this.component = _c;
        down = _d;
    }
    @Override
    public void action() {
        component.focusBound(down);
    }
}
