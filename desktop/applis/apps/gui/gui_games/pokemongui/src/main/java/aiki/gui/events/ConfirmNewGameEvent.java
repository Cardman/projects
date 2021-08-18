package aiki.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.WindowAiki;

public class ConfirmNewGameEvent extends MouseAdapter {

    private WindowAiki window;

    public ConfirmNewGameEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.confirmNewGame();
    }
}
