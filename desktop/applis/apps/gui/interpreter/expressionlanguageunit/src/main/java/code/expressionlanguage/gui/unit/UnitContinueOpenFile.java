package code.expressionlanguage.gui.unit;

import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class UnitContinueOpenFile implements AbsContinueFile {
    private final WindowUnit mainWindow;
    private final TestableFrame tested;

    public UnitContinueOpenFile(WindowUnit _mainWindow, TestableFrame _tested) {
        mainWindow = _mainWindow;
        tested = _tested;
    }
    @Override
    public void next(FileDialogContent _content) {
        mainWindow.launchFileConf(_content.getSelectedAbsolutePath(), tested);
    }
}
