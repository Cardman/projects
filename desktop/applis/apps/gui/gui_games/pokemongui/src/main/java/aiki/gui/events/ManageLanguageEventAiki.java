package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

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
