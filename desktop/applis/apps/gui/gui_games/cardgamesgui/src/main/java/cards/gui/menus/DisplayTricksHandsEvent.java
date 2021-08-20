package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.AbsActionListener;

public class DisplayTricksHandsEvent implements AbsActionListener {

    private WindowCards window;

    public DisplayTricksHandsEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.displayTricksHands();
    }
}
