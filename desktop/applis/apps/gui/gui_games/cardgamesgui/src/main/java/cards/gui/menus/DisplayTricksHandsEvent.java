package cards.gui.menus;

import cards.gui.WindowCardsInt;
import code.gui.events.AbsActionListener;

public class DisplayTricksHandsEvent implements AbsActionListener {

    private WindowCardsInt window;

    public DisplayTricksHandsEvent(WindowCardsInt _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.displayTricksHands();
    }
}
