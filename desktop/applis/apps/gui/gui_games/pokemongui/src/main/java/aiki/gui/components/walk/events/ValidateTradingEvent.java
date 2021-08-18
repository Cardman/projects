package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.WindowAiki;
import aiki.network.stream.Ok;

public class ValidateTradingEvent extends MouseAdapter {

    private WindowAiki window;

    public ValidateTradingEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.sendObject(Ok.INSTANCE);
    }
}
