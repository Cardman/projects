package aiki.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.WindowAiki;

public class SaveGameEventAiki implements ActionListener {

    private WindowAiki window;

    public SaveGameEventAiki(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.saveGame();
    }

}
