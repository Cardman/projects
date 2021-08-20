package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class LoadGameEventCards implements AbsActionListener {

    private WindowCards window;

    public LoadGameEventCards(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.loadGame();
    }
}
