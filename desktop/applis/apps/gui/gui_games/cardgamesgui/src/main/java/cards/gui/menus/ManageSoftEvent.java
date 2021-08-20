package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class ManageSoftEvent implements AbsActionListener {

    private WindowCards window;

    private String key;

    public ManageSoftEvent(WindowCards _window, String _key) {
        window = _window;
        key = _key;
    }

    @Override
    public void action() {
        window.manageSoft(key);
    }
}
