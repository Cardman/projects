package cards.gui.menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.MainWindow;

public class ManageSoftEvent implements ActionListener {

    private MainWindow window;

    private String key;

    public ManageSoftEvent(MainWindow _window, String _key) {
        window = _window;
        key = _key;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.manageSoft(key);
    }
}
