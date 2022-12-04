package aiki.gui.components.walk.events;

import code.gui.events.AbsActionListener;
import code.network.WindowNetWork;

public class ValidateTradingEvent implements AbsActionListener {

    private WindowNetWork window;

    public ValidateTradingEvent(WindowNetWork _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.sendObjectOk();
    }
}
