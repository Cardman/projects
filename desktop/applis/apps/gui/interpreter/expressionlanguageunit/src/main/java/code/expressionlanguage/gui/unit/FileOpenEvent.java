package code.expressionlanguage.gui.unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class FileOpenEvent implements ActionListener {
    private MainWindow mainWindow;

    public FileOpenEvent(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        mainWindow.selectFile();
    }
}
