package cards.gui.menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.MainWindow;

public class ChangeGameEvent implements ActionListener {

    private MainWindow window;

    public ChangeGameEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.changeGame();
    }
}
