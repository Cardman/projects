package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectHomeEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectHomeEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.goTop();
    }
}
