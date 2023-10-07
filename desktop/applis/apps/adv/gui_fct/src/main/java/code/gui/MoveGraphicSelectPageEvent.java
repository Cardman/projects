package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectPageEvent<T> implements AbsActionListener {
    private final int down;
    private final ScrollCustomGraphicList<T> component;

    public MoveGraphicSelectPageEvent(ScrollCustomGraphicList<T> _c, int _d) {
        this.component = _c;
        this.down = _d;
    }
    @Override
    public void action() {
        component.movePage(down);
    }
}
