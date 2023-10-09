package code.gui;

import code.gui.events.AbsActionListener;

public final class MoveGraphicSelectAddAncIntervalEvent<T> implements AbsActionListener {
    private final ScrollCustomGraphicList<T> component;
    private final boolean value;

    public MoveGraphicSelectAddAncIntervalEvent(ScrollCustomGraphicList<T> _c, boolean _v) {
        this.component = _c;
        value = _v;
    }
    @Override
    public void action() {
        component.addIntToSelection(value);
    }
}
