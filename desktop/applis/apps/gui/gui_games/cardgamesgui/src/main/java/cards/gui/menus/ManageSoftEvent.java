package cards.gui.menus;

import cards.gui.WindowCardsInt;
import code.gui.events.AbsActionListener;

public class ManageSoftEvent implements AbsActionListener {

    private WindowCardsInt window;

    private String key;

    public ManageSoftEvent(WindowCardsInt _window, String _key) {
        window = _window;
        key = _key;
    }

    @Override
    public void action() {
        window.manageSoft(key);
    }
}
