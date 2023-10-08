package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectDeSelectAllEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectDeSelectAllEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.deselectAllAction();
    }
}
