package aiki.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.MainWindow;

public class ConfirmNewGameEvent extends MouseAdapter {

    private MainWindow window;

    public ConfirmNewGameEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.confirmNewGame();
    }
}
