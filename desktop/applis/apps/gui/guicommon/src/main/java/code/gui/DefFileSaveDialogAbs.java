package code.gui;

import code.gui.initialize.AbstractProgramInfos;

public final class DefFileSaveDialogAbs implements FileSaveDialogAbs{

    private final FileSaveDialog fileSaveDialog;

    public DefFileSaveDialogAbs(AbstractProgramInfos _pr) {
        fileSaveDialog = new FileSaveDialog(_pr);
    }

    @Override
    public String input(AbsCommonFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        FileSaveDialog.setFileSaveDialogByFrame(_w, _language, _currentFolderRoot, _extension,_folder, fileSaveDialog);
        return FileSaveDialog.getStaticSelectedPath(fileSaveDialog);
    }

    @Override
    public String input(AbsCommonFrame _c, AbsDialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        FileSaveDialog.setFileSaveDialog(_c,_w,_language,_currentFolderRoot,_extension,_folder, fileSaveDialog);
        return FileSaveDialog.getStaticSelectedPath(fileSaveDialog);
    }
}
