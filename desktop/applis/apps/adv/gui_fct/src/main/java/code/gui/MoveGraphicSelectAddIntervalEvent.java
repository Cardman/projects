package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectAddIntervalEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectAddIntervalEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.addToSelection();
    }
}
