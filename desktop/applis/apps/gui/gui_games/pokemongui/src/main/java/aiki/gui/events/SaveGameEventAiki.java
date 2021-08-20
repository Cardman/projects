package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

public class SaveGameEventAiki implements AbsActionListener {

    private WindowAiki window;

    public SaveGameEventAiki(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.saveGame();
    }

}
