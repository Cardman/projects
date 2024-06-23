package cards.gui.menus;

import cards.gui.*;
import code.gui.events.*;

public final class ChangeGameEvent implements AbsActionListener {

    private final WindowCards window;

    public ChangeGameEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.changeGame();
    }
}
