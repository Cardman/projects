package aiki.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.MainWindow;

public class LoadZipEvent implements ActionListener {

    private MainWindow window;
    private boolean folder;

    public LoadZipEvent(MainWindow _window, boolean _folder) {
        window = _window;
        folder = _folder;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.loadZip(folder);
    }

}
