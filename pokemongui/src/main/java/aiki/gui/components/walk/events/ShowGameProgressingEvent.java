package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.MainWindow;

public class ShowGameProgressingEvent extends MouseAdapter {

    private MainWindow window;

    public ShowGameProgressingEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.showGameProgressing();
    }
}
