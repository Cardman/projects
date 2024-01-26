package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.FolderOpenDialogAbs;
import code.gui.initialize.AbstractProgramInfos;

public final class DefFolderOpenDialogAbs implements FolderOpenDialogAbs {

    private final FolderOpenDialog folderOpenDialog;

    public DefFolderOpenDialogAbs(AbstractProgramInfos _programInfos) {
        folderOpenDialog = new FolderOpenDialog(_programInfos);
    }
    @Override
    public String input(AbsCommonFrame _w, boolean _currentFolderRoot) {
        FolderOpenDialog.setFolderOpenDialog(_currentFolderRoot, folderOpenDialog, _w);
        return FolderOpenDialog.getStaticSelectedPath(folderOpenDialog);
    }
}
