package cards.gui.events;

import cards.gui.WindowCardsInt;
import code.gui.events.AbsActionListener;

public class BackToMainMenuEvent implements AbsActionListener {

    private WindowCardsInt window;

    public BackToMainMenuEvent(WindowCardsInt _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.menuPrincipal();
        window.pack();
    }
}
