package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.WindowCards;

public class BackToMainMenuEvent extends MouseAdapter {

    private WindowCards window;

    public BackToMainMenuEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.menuPrincipal();
        window.pack();
    }
}
