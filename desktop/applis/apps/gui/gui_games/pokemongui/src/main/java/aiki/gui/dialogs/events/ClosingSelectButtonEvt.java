package aiki.gui.dialogs.events;

import aiki.gui.WindowAiki;
import code.gui.AbsCommonFrame;
import code.gui.events.AbsActionListener;

public final class ClosingSelectButtonEvt implements AbsActionListener {

    private final AbsCommonFrame frame;
    private final WindowAiki window;
    public ClosingSelectButtonEvt(AbsCommonFrame _current, WindowAiki _w) {
        frame = _current;
        window = _w;
    }
    @Override
    public void action() {
        frame.setVisible(false);
        window.getModal().set(false);
    }
}
