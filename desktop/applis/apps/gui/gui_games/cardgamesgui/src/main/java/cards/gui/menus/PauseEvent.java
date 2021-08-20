package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class PauseEvent implements AbsActionListener {

    private WindowCards window;

    public PauseEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.pause();
    }
}
