package code.gui;

public final class DefFileSaveDialogAbs implements FileSaveDialogAbs{
    @Override
    public String input(GroupFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        FileSaveDialog.setFileSaveDialogByFrame(_w, _language, _currentFolderRoot, _extension,_folder, _w.getFrames().getHomePath());
        return FileSaveDialog.getStaticSelectedPath(_w.getFileSaveDialog());
    }

    @Override
    public String input(GroupFrame _c, AbsDialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        FileSaveDialog.setFileSaveDialog(_c,_w,_language,_currentFolderRoot,_extension,_folder,_c.getFrames().getHomePath());
        return FileSaveDialog.getStaticSelectedPath(_c.getFileSaveDialog());
    }
}
