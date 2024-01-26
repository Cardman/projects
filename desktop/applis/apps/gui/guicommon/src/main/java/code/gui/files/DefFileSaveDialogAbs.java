package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.AbsDialog;
import code.gui.FileSaveDialogAbs;
import code.gui.initialize.AbstractProgramInfos;

public final class DefFileSaveDialogAbs implements FileSaveDialogAbs {

    private final FileSaveDialog fileSaveDialog;

    public DefFileSaveDialogAbs(AbstractProgramInfos _pr) {
        fileSaveDialog = new FileSaveDialog(_pr);
    }

    @Override
    public String input(AbsCommonFrame _w, boolean _currentFolderRoot, String _extension, String _folder) {
        FileSaveDialog.setFileSaveDialogByFrame(_w, _currentFolderRoot, _folder, fileSaveDialog);
        return FileSaveDialog.getStaticSelectedPath(fileSaveDialog);
    }

    @Override
    public String input(AbsCommonFrame _c, AbsDialog _w, boolean _currentFolderRoot, String _extension, String _folder) {
        FileSaveDialog.setFileSaveDialog(_w, _currentFolderRoot, _folder, fileSaveDialog);
        return FileSaveDialog.getStaticSelectedPath(fileSaveDialog);
    }
}
