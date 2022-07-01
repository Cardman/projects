package code.gui;

public final class DefFolderOpenDialogAbs implements FolderOpenDialogAbs {
    @Override
    public String input(AbsGroupFrame _w, String _language, boolean _currentFolderRoot) {
        FolderOpenDialog.setFolderOpenDialog((GroupFrame) _w, _language, _currentFolderRoot);
        return FolderOpenDialog.getStaticSelectedPath(((GroupFrame)_w).getFolderOpenDialog());
    }
}
