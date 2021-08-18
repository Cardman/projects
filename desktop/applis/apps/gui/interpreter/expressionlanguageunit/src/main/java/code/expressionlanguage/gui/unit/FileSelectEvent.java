package code.expressionlanguage.gui.unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class FileSelectEvent implements ActionListener {
    private WindowUnit mainWindow;
    private SimpleFilesFrame tested;

    public FileSelectEvent(WindowUnit _mainWindow, SimpleFilesFrame _tested) {
        mainWindow = _mainWindow;
        tested = _tested;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        mainWindow.getThreadFactory().newStartedThread(new LoadConf(tested,mainWindow.selectedFile()));
    }
}
