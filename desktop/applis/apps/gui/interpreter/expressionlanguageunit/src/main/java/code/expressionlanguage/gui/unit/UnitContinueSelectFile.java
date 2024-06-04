package code.expressionlanguage.gui.unit;

import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class UnitContinueSelectFile implements AbsContinueFile {
    private final WindowUnit mainWindow;
    private final SimpleFilesFrame tested;

    public UnitContinueSelectFile(WindowUnit _mainWindow, SimpleFilesFrame _tested) {
        mainWindow = _mainWindow;
        tested = _tested;
    }
    @Override
    public void next(FileDialogContent _content) {
        mainWindow.getThreadFactory().newStartedThread(new LoadConf(tested,_content.getSelectedAbsolutePath()));
    }
}
