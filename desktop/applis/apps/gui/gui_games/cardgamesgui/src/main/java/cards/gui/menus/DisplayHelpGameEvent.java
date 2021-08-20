package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.AbsActionListener;

public class DisplayHelpGameEvent implements AbsActionListener {

    private WindowCards window;

    public DisplayHelpGameEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.displayHelpGame();
    }
}
