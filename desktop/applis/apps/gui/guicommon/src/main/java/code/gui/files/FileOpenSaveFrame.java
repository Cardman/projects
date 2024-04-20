package code.gui.files;

import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;

public final class FileOpenSaveFrame extends AbsOpenSaveFrame {
    private final FileOpenDialogContent fileOpenDialogContent;
    private final FileSaveDialogContent fileSaveDialogContent;
    public FileOpenSaveFrame(AbstractProgramInfos _frameFact, AbstractAtomicBoolean _m) {
        super(_frameFact, _m);
        fileOpenDialogContent = new FileOpenDialogContent(_frameFact.getThreadFactory().newAtomicBoolean(), _frameFact.getThreadFactory().newAtomicBoolean(),_frameFact);
        fileSaveDialogContent = new FileSaveDialogContent(_frameFact, true);
    }

    public static void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folder, FileOpenSaveFrame _fileSave, AbsSaveFile _s, AbsContinueFile _c) {
        setFileSaveDialogByFrame(_currentFolderRoot, _folder, _folder, _fileSave, _s, _c);
    }
    public static void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folderSave, String _folderOpen, FileOpenSaveFrame _fileSave, AbsSaveFile _s, AbsContinueFile _c) {
        FileOpenDialogContent o_ = _fileSave.getFileOpenDialogContent();
        FileSaveDialogContent s_ = _fileSave.getFileSaveDialogContent();
        _fileSave.common();
        s_.setFileSaveContentDialogByFrame(_currentFolderRoot,_folderSave, new OpenSavePostFileFrameEvent(_fileSave, s_, FileSaveDialog.FILE_SAVE_DIAL, MessagesFileSaveDialog.SAVE, _fileSave.getTabs(), _fileSave.getLabSave()));
        o_.setFileOpenDialogPart(_currentFolderRoot,_folderOpen, new OpenSavePostFileFrameEvent(_fileSave, o_, FileOpenDialog.FILE_OPEN_DIAL, MessagesFileOpenDialog.OPEN, _fileSave.getTabs(), _fileSave.getLabOpen()));
        _fileSave.common(_s,_c,o_,s_);
    }

    public FileOpenDialogContent getFileOpenDialogContent() {
        return fileOpenDialogContent;
    }

    public FileSaveDialogContent getFileSaveDialogContent() {
        return fileSaveDialogContent;
    }
}
