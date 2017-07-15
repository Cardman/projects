package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.MainWindow;

public class ChooseModeEvent extends MouseAdapter {

    private MainWindow window;

    private boolean single;

    public ChooseModeEvent(MainWindow _window, boolean _single) {
        window = _window;
        single = _single;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.setSingle(single);
        if (single) {
            window.menuSoloGames();
        } else {
            window.menuMultiGames();
        }
    }
}
