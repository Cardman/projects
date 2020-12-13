package code.expressionlanguage.gui.unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SimpleFilesFrameOpen implements ActionListener {
    private MainWindow mainWindow;

    public SimpleFilesFrameOpen(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        mainWindow.open();
    }
}

