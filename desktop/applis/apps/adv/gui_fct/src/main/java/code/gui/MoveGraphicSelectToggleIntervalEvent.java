package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectToggleIntervalEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectToggleIntervalEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.toggleSelection();
    }
}
