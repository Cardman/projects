package code.gui;

public interface FileSaveDialogAbs {
    String input(GroupFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder);
    String input(GroupFrame _c, AbsDialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder);
}
