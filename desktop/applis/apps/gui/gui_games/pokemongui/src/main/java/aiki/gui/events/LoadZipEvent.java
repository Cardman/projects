package aiki.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.WindowAiki;

public class LoadZipEvent implements ActionListener {

    private WindowAiki window;
    private boolean folder;

    public LoadZipEvent(WindowAiki _window, boolean _folder) {
        window = _window;
        folder = _folder;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.loadZip(folder);
    }

}
