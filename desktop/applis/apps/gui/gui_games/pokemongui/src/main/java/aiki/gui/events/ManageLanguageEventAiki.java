package aiki.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.WindowAiki;
import code.gui.AbsActionListener;

public class ManageLanguageEventAiki implements AbsActionListener {

    private WindowAiki window;

    public ManageLanguageEventAiki(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.manageLanguage();
    }

}
