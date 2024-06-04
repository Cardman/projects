package code.gui.files;

import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;

public final class FileOpenFrame extends FileFrame implements FileFrameInt {
    public static final String FILE_OPEN_DIAL = "file_open";
    private final FileOpenDialogContent fileDialogContent;
    public FileOpenFrame(AbstractProgramInfos _frameFact, AbstractAtomicBoolean _m) {
        super(_frameFact, _m);
        fileDialogContent = new FileOpenDialogContent(_frameFact.getThreadFactory().newAtomicBoolean(), _frameFact.getThreadFactory().newAtomicBoolean(),_frameFact);
    }

    public static void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folder, FileOpenFrame _fileSave, AbsButtonsOpenPanel _cont) {
        FileOpenDialogContent c_ = _fileSave.getFileDialogContent();
        c_.setFileOpenDialog(_currentFolderRoot,_folder, new DefPostFileFrameEvent(_fileSave, c_, _fileSave.getFrame()),_cont);
    }

    @Override
    public void closeFrameFile() {
        getClosing().windowClosing();
    }
    public FileOpenDialogContent getFileDialogContent() {
        return fileDialogContent;
    }
}
