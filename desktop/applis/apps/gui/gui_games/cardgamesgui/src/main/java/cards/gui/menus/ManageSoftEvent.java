package cards.gui.menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.WindowCards;

public class ManageSoftEvent implements ActionListener {

    private WindowCards window;

    private String key;

    public ManageSoftEvent(WindowCards _window, String _key) {
        window = _window;
        key = _key;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.manageSoft(key);
    }
}
