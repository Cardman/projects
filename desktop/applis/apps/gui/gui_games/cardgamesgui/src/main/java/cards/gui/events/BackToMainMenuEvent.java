package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.MainWindow;

public class BackToMainMenuEvent extends MouseAdapter {

    private MainWindow window;

    public BackToMainMenuEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.menuPrincipal();
        window.pack();
    }
}
