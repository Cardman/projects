package cards.gui.menus;

import cards.gui.WindowCardsInt;
import code.gui.events.AbsActionListener;

public class ManageLanguageEventCards implements AbsActionListener {

    private WindowCardsInt window;

    public ManageLanguageEventCards(WindowCardsInt _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.manageLanguage();
    }
}
