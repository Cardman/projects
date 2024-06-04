package code.gui.files;

import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;

public final class FileSaveFrame extends FileFrame implements FileFrameInt {
    public static final String FILE_SAVE_DIAL = "file_save";
    private final FileSaveDialogContent fileDialogContent;
    public FileSaveFrame(AbstractProgramInfos _frameFact, AbstractAtomicBoolean _m) {
        super(_frameFact, _m);
        fileDialogContent = new FileSaveDialogContent(_frameFact);
    }

    public static void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folder, FileSaveFrame _fileSave, AbsButtonsSavePanel _build) {
        FileSaveDialogContent c_ = _fileSave.getFileDialogContent();
        c_.setFileSaveDialogByFrame(_currentFolderRoot,_folder, new DefPostFileFrameEvent(_fileSave, c_, _fileSave.getFrame()), _build);
    }

    @Override
    public void closeFrameFile() {
        getClosing().windowClosing();
    }
    public FileSaveDialogContent getFileDialogContent() {
        return fileDialogContent;
    }
//
//    public static void setFileSaveDialog(String _language, boolean _currentFolderRoot, String _folder, FileSaveFrame _fileSave, AbsButtonsSavePanel _build) {
//        ((FileSaveDialogContent)_fileSave.getFileDialogContent()).setFileSaveDialog(_language,_currentFolderRoot,_folder, new DefPostFileFrameEvent(_fileSave), _build);
//    }
}
