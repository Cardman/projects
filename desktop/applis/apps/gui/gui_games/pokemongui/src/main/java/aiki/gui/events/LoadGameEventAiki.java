package aiki.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.WindowAiki;

public class LoadGameEventAiki implements ActionListener {

    private WindowAiki window;

    public LoadGameEventAiki(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.loadGame();
    }

}
