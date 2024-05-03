package aiki.gui.dialogs.events;

import aiki.gui.dialogs.SelectDialog;
import code.gui.events.AbsActionListener;

public final class ClosingSelectButtonEvt implements AbsActionListener {

    private final SelectDialog frame;

    public ClosingSelectButtonEvt(SelectDialog _current) {
        frame = _current;
    }
    @Override
    public void action() {
        frame.closeWindow();
    }
}
