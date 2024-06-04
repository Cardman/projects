package code.expressionlanguage.gui.unit;

import code.gui.events.AbsActionListener;
import code.gui.files.DefButtonsOpenPanelAct;
import code.gui.files.FileOpenFrame;


public final class FileSelectEvent implements AbsActionListener {
    private final WindowUnit mainWindow;
    private final SimpleFilesFrame tested;

    public FileSelectEvent(WindowUnit _mainWindow, SimpleFilesFrame _tested) {
        mainWindow = _mainWindow;
        tested = _tested;
    }

    @Override
    public void action() {
        mainWindow.getAtomicBoolean().set(true);
        FileOpenFrame.setFileSaveDialogByFrame(true,mainWindow.getFrames().getHomePath(),mainWindow.getFileOpenFrame(),new DefButtonsOpenPanelAct(new UnitContinueSelectFile(mainWindow,tested)));
//        mainWindow.getThreadFactory().newStartedThread(new LoadConf(tested,mainWindow.selectedFile()));
    }
}
