package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectPageUpEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectPageUpEvent(ScrollCustomGraphicList<T> _c) {
        this.component = _c;
    }
    @Override
    public void action() {
        component.movePageUp();
    }
}
