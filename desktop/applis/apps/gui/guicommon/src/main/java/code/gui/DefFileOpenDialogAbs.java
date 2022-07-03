package code.gui;

import code.gui.initialize.AbstractProgramInfos;

public final class DefFileOpenDialogAbs implements FileOpenDialogAbs{

    private final FileOpenDialog fileOpenDialog;

    public DefFileOpenDialogAbs(AbstractProgramInfos _pr) {
        fileOpenDialog = new FileOpenDialog(_pr.getThreadFactory().newAtomicBoolean(), _pr.getThreadFactory().newAtomicBoolean(),_pr);
    }

    @Override
    public String input(AbsCommonFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        FileOpenDialog.setFileOpenDialog(_language, _currentFolderRoot, _extension, _folder, fileOpenDialog, _w);
        return FileOpenDialog.getStaticSelectedPath(fileOpenDialog);
    }
}
