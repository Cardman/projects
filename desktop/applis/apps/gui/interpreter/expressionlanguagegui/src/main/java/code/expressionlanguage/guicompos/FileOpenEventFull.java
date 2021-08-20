package code.expressionlanguage.guicompos;

import code.gui.AbsActionListener;

public final class FileOpenEventFull implements AbsActionListener {
    private WindowFull mainWindow;

    public FileOpenEventFull(WindowFull _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void action() {
        mainWindow.selectFile();
    }
}
