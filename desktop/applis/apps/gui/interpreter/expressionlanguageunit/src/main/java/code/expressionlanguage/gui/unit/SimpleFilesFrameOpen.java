package code.expressionlanguage.gui.unit;

import code.gui.events.AbsActionListener;

public final class SimpleFilesFrameOpen implements AbsActionListener {
    private WindowUnit mainWindow;

    public SimpleFilesFrameOpen(WindowUnit _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void action() {
        mainWindow.open();
    }
}

