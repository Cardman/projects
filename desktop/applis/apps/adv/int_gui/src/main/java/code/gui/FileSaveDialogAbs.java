package code.gui;

public interface FileSaveDialogAbs {
    String input(AbsCommonFrame _w, boolean _currentFolderRoot, String _extension, String _folder);
    String input(AbsCommonFrame _c, AbsDialog _w, boolean _currentFolderRoot, String _extension, String _folder);
}
