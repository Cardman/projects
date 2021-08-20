package code.expressionlanguage.guicompos;

import code.gui.events.AbsActionListener;

public final class ListenerLaunchApp implements AbsActionListener {
    private WindowFull mainWindow;

    public ListenerLaunchApp(WindowFull _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void action() {
        mainWindow.process();
    }
}
