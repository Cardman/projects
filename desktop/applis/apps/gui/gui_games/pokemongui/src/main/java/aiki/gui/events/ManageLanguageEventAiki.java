package aiki.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.WindowAiki;

public class ManageLanguageEventAiki implements ActionListener {

    private WindowAiki window;

    public ManageLanguageEventAiki(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.manageLanguage();
    }

}
