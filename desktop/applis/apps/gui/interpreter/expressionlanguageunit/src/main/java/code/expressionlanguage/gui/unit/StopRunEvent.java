package code.expressionlanguage.gui.unit;

import code.gui.AbsActionListener;

public final class StopRunEvent implements AbsActionListener {
    private WindowUnit mainWindow;

    public StopRunEvent(WindowUnit _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void action() {
        mainWindow.stop();
    }
}