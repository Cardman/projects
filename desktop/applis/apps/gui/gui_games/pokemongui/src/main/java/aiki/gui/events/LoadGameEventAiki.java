package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

public class LoadGameEventAiki implements AbsActionListener {

    private WindowAiki window;

    public LoadGameEventAiki(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.loadGame();
    }

}
