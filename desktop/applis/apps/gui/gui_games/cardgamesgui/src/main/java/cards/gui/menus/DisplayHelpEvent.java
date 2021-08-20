package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.AbsActionListener;

public class DisplayHelpEvent implements AbsActionListener {

    private WindowCards window;

    public DisplayHelpEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.displayHelp();
    }
}
