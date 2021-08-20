package code.expressionlanguage.gui.unit;

import code.gui.events.AbsActionListener;

public final class LogErrEvent implements AbsActionListener {
    private WindowUnit mainWindow;

    public LogErrEvent(WindowUnit _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void action() {
        mainWindow.logErr();
    }
}