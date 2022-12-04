package aiki.gui.events;

import aiki.gui.WindowAikiInt;
import code.gui.events.AbsActionListener;

public class LoadZipEvent implements AbsActionListener {

    private WindowAikiInt window;
    private boolean folder;

    public LoadZipEvent(WindowAikiInt _window, boolean _folder) {
        window = _window;
        folder = _folder;
    }

    @Override
    public void action() {
        window.loadZip(folder);
    }

}
