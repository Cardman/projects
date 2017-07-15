package cards.gui.menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.MainWindow;

public class QuitMultiEvent implements ActionListener {

    private MainWindow window;

    public QuitMultiEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.quitMulti();
    }
}
