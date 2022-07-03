package code.gui;

public interface FileSaveDialogAbs {
    String input(AbsGroupFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder);
    String input(AbsGroupFrame _c, AbsDialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder);
}
