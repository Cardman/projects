package aiki.gui.events;

import aiki.gui.WindowAikiInt;
import code.gui.events.AbsActionListener;

public class SaveGameEventAiki implements AbsActionListener {

    private WindowAikiInt window;

    public SaveGameEventAiki(WindowAikiInt _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.saveGame();
    }

}
