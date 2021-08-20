package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.AbsActionListener;

public class ManageLanguageEventCards implements AbsActionListener {

    private WindowCards window;

    public ManageLanguageEventCards(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.manageLanguage();
    }
}
