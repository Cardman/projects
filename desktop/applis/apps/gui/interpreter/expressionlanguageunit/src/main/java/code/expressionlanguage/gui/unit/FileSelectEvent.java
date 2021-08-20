package code.expressionlanguage.gui.unit;

import code.gui.events.AbsActionListener;


public final class FileSelectEvent implements AbsActionListener {
    private WindowUnit mainWindow;
    private SimpleFilesFrame tested;

    public FileSelectEvent(WindowUnit _mainWindow, SimpleFilesFrame _tested) {
        mainWindow = _mainWindow;
        tested = _tested;
    }

    @Override
    public void action() {
        mainWindow.getThreadFactory().newStartedThread(new LoadConf(tested,mainWindow.selectedFile()));
    }
}
