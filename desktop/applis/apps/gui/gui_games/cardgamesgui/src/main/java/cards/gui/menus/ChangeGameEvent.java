package cards.gui.menus;

import cards.gui.WindowCardsInt;
import code.gui.events.AbsActionListener;

public class ChangeGameEvent implements AbsActionListener {

    private WindowCardsInt window;

    public ChangeGameEvent(WindowCardsInt _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.changeGame();
    }
}
