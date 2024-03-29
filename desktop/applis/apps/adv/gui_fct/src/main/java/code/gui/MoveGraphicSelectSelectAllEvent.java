package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectSelectAllEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectSelectAllEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.selectAll();
    }
}
