package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class ChangeGameEvent implements AbsActionListener {

    private WindowCards window;

    public ChangeGameEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.changeGame();
    }
}
