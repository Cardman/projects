package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

public class ShowDataWebEvent implements AbsActionListener {

    private WindowAiki window;

    public ShowDataWebEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.showDataWeb();
    }

}
