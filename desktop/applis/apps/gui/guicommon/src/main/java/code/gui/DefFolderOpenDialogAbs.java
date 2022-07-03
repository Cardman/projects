package code.gui;

import code.gui.initialize.AbstractProgramInfos;

public final class DefFolderOpenDialogAbs implements FolderOpenDialogAbs {

    private final FolderOpenDialog folderOpenDialog;

    public DefFolderOpenDialogAbs(AbstractProgramInfos _programInfos) {
        folderOpenDialog = new FolderOpenDialog(_programInfos);
    }
    @Override
    public String input(AbsCommonFrame _w, String _language, boolean _currentFolderRoot) {
        FolderOpenDialog.setFolderOpenDialog(_language, _currentFolderRoot, folderOpenDialog, _w);
        return FolderOpenDialog.getStaticSelectedPath(folderOpenDialog);
    }
}
