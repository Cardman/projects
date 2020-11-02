package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.MainWindow;
import aiki.network.stream.Ok;

public class ValidateTradingEvent extends MouseAdapter {

    private MainWindow window;

    public ValidateTradingEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.sendObject(Ok.INSTANCE);
    }
}
