package aiki.gui.events;

import aiki.gui.WindowAikiInt;
import code.gui.events.AbsActionListener;

public class LoadGameEventAiki implements AbsActionListener {

    private WindowAikiInt window;

    public LoadGameEventAiki(WindowAikiInt _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.loadGame();
    }

}
