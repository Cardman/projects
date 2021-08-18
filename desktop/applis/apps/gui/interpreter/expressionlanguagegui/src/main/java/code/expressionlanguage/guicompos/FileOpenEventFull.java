package code.expressionlanguage.guicompos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class FileOpenEventFull implements ActionListener {
    private WindowFull mainWindow;

    public FileOpenEventFull(WindowFull _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        mainWindow.selectFile();
    }
}
