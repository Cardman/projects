package code.gui;

public interface FileSaveDialogAbs {
    String input(AbsCommonFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder);
    String input(AbsCommonFrame _c, AbsDialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder);
}
