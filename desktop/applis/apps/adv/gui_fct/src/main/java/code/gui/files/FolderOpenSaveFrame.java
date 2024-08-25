package code.gui.files;

import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;

public final class FolderOpenSaveFrame extends AbsOpenSaveFrame {
    private final FolderOpenDialogContent folderOpenDialogContent;
    private final FileSaveDialogContent fileSaveDialogContent;
    public FolderOpenSaveFrame(AbstractProgramInfos _frameFact, AbstractAtomicBoolean _m) {
        super(_frameFact, _m);
        folderOpenDialogContent = new FolderOpenDialogContent(_frameFact);
        fileSaveDialogContent = new FileSaveDialogContent(_frameFact, true);
    }

    public static void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folder, FolderOpenSaveFrame _fileSave, AbsSaveFile _s, AbsContinueFile _c) {
        FolderOpenDialogContent o_ = _fileSave.getFolderOpenDialogContent();
        FileSaveDialogContent s_ = _fileSave.getFileSaveDialogContent();
        _fileSave.common();
        s_.setFileSaveContentDialogByFrame(_currentFolderRoot,_folder, new OpenSavePostFileFrameEvent(_fileSave, s_, MessagesGuiFct.FILE_SAVE_DIAL, MessagesFileSaveDialog.SAVE, _fileSave.getTabs(), _fileSave.getLabSave()));
        o_.setFolderOpenDialogPart(_currentFolderRoot, new OpenSavePostFileFrameEvent(_fileSave, o_, MessagesGuiFct.FOLDER_OPEN_DIAL, MessagesFolderOpenDialog.OPEN, _fileSave.getTabs(), _fileSave.getLabOpen()));
        _fileSave.common(_s,_c,o_,s_);
    }

    public FolderOpenDialogContent getFolderOpenDialogContent() {
        return folderOpenDialogContent;
    }

    public FileSaveDialogContent getFileSaveDialogContent() {
        return fileSaveDialogContent;
    }
}
