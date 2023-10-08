package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectExtendToEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectExtendToEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.extendTo();
    }
}
