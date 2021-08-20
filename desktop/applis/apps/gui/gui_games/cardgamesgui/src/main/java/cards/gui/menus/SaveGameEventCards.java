package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class SaveGameEventCards implements AbsActionListener {

    private WindowCards window;

    public SaveGameEventCards(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.saveGame();
    }
}
