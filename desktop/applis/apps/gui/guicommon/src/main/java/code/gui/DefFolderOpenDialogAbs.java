package code.gui;

public final class DefFolderOpenDialogAbs implements FolderOpenDialogAbs {
    @Override
    public String input(GroupFrame _w, String _language, boolean _currentFolderRoot) {
        FolderOpenDialog.setFolderOpenDialog(_w, _language, _currentFolderRoot);
        return FolderOpenDialog.getStaticSelectedPath(_w.getFolderOpenDialog());
    }
}
