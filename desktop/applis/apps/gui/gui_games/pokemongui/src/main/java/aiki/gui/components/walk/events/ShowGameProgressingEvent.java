package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.WindowAiki;

public class ShowGameProgressingEvent extends MouseAdapter {

    private WindowAiki window;

    public ShowGameProgressingEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.showGameProgressing();
    }
}
