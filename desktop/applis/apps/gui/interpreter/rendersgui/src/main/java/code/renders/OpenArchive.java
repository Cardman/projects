package code.renders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class OpenArchive implements ActionListener {

    private WindowRenders mainWindow;

    public OpenArchive(WindowRenders _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        mainWindow.load();
    }
}
