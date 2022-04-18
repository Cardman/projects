package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

public class ConfirmNewGameEvent implements AbsActionListener {

    private WindowAiki window;

    public ConfirmNewGameEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.confirmNewGame();
    }
}
