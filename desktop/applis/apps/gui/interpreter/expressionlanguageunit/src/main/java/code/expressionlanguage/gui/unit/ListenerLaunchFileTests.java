package code.expressionlanguage.gui.unit;

import code.gui.events.AbsActionListener;

public final class ListenerLaunchFileTests implements AbsActionListener {
    private WindowUnit mainWindow;
    private SimpleFilesFrame tested;

    public ListenerLaunchFileTests(WindowUnit _mainWindow, SimpleFilesFrame _tested) {
        mainWindow = _mainWindow;
        tested = _tested;
    }

    @Override
    public void action() {
        mainWindow.launchFileConf(tested.getFilePath(),tested);
    }
}
