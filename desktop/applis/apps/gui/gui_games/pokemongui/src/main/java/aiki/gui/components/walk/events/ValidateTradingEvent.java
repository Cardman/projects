package aiki.gui.components.walk.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

public class ValidateTradingEvent implements AbsActionListener {

    private WindowAiki window;

    public ValidateTradingEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.sendObjectOk();
    }
}
