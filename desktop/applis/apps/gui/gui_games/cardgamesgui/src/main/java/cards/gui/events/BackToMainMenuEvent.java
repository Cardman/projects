package cards.gui.events;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class BackToMainMenuEvent implements AbsActionListener {

    private WindowCards window;

    public BackToMainMenuEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.menuPrincipal();
        window.pack();
    }
}
