package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

public class LoadZipEvent implements AbsActionListener {

    private WindowAiki window;
    private boolean folder;

    public LoadZipEvent(WindowAiki _window, boolean _folder) {
        window = _window;
        folder = _folder;
    }

    @Override
    public void action() {
        window.loadZip(folder);
    }

}
