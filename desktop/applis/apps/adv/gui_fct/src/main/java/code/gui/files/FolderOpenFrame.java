package code.gui.files;

import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;

public final class FolderOpenFrame extends FileFrame implements FileFrameInt {
    private final FolderOpenDialogContent folderOpenDialogContent;
    public FolderOpenFrame(AbstractProgramInfos _frameFact, AbstractAtomicBoolean _m) {
        super(_frameFact, _m);
        folderOpenDialogContent = new FolderOpenDialogContent(_frameFact);
    }

    public static void setFolderOpenDialog(boolean _currentFolderRoot, FolderOpenFrame _folder,AbsButtonsOpenFolderPanel _build) {
        FolderOpenDialogContent c_ = _folder.getFolderOpenDialogContent();
        c_.setFolderOpenDialog(_currentFolderRoot, new DefPostFileFrameEvent(_folder, c_, _folder.getFrame()),_build);
    }
    @Override
    public void closeFrameFile() {
        getClosing().windowClosing();
    }

    public FolderOpenDialogContent getFolderOpenDialogContent() {
        return folderOpenDialogContent;
    }
}
